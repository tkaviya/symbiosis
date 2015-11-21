package net.blaklizt.symbiosis.sym_sync.server.queue;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Format;
import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserOptionDao;
import net.blaklizt.symbiosis.sym_persistence.entity.simple_type.symbiosis_user_option;
import net.blaklizt.symbiosis.sym_persistence.helper.OptionHelper;
import net.blaklizt.symbiosis.sym_sync.server.file.SymSyncFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.Singleton;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * ***************************************************************************
 * *
 * Created:     26 / 09 / 2015                                             *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 * *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 * *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *
 * ****************************************************************************
 */

@Service
@Singleton
public class SymSyncFileDemandQueue implements SymSyncDataQueue {

    private static final Logger logger = Configuration.getNewLogger(SymSyncFileDemandQueue.class.getSimpleName());

    @Autowired SymbiosisUserOptionDao symbiosisUserOptionDao;

    @Autowired SymSyncFileMessageQueue symSyncFileMessageQueue;

    @Autowired SymSyncClientPublisher symSyncClientPublisher;

    private HashMap<Long, LinkedList<SymSyncFile>> userFiles = new HashMap<>();


    protected static final int MAX_FILES = 3;

    protected static MessageDigest messageDigest;

    protected byte[] fileBytes = new byte[1024];

    private SymSyncFileDemandQueue()
    {
        try { messageDigest = MessageDigest.getInstance("SHA-512"); }
        catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
    }

    @Override
    public void prepareFileQueue(final Long symbiosisUserId, boolean forceRefresh) {

        if (userFiles.get(symbiosisUserId) != null && !forceRefresh)
            return;

        ThreadPoolManager.schedule(new Runnable() {
            @Override
            public void run() {
                logger.info("Checking for files to sync for user " + symbiosisUserId);

                symbiosis_user_option syncFolderLocation = symbiosisUserOptionDao.findByUserIDAndOption(
                        symbiosisUserId, OptionHelper.SYNC_FOLDER.value());

                if (syncFolderLocation != null) {
                    logger.info("File list folder = " + syncFolderLocation.getOption_value());

                    //clear all existing data for that user
                    if (userFiles.get(symbiosisUserId) != null) userFiles.get(symbiosisUserId).clear();

                    getFileHashes(symbiosisUserId, syncFolderLocation.getOption_value(), true);
                }
                else {
                    logger.warning(format("User preference %s was not found for user %s.", OptionHelper.SYNC_FOLDER.name(), symbiosisUserId));
                }
            }
        });
    }

    @Override
    public void prepareDefaultFileQueue(boolean forceRefresh) {
        prepareFileQueue(0L, forceRefresh);
    }

    @Override
    public LinkedList<SymSyncFile> getFiles(Long symbiosisUserId) {
        return symSyncClientPublisher.getFiles(symbiosisUserId);
    }

    public void getFileHashes(Long symbiosisUserId, String folder, boolean recursive)
    {
        if (userFiles.get(symbiosisUserId) != null && userFiles.get(symbiosisUserId).size() >= MAX_FILES) {
            logger.fine("Processing limit reached! Skipping " + folder);
            return;
        }

        logger.info("Recursive = " + (recursive ? 1 : 0) + " | Processing path " + folder);

        File location = new File(folder);
        if (location.isDirectory() && !location.getName().equals(".") && !location.getName().equals("..") && recursive)
        {
            File[] files = location.listFiles();
            for (File file : files)
            {
                folder = file.getAbsolutePath();
                getFileHashes(symbiosisUserId, folder, recursive);
            }
        }
        else if (location.isFile() && !location.getName().equals(".") && !location.getName().equals(".."))
        {
            if (userFiles.get(symbiosisUserId) == null) userFiles.put(symbiosisUserId, new LinkedList<SymSyncFile>());

            logger.info(CommonUtilities.alignStringToLength(
                    String.valueOf(userFiles.get(symbiosisUserId).size()), 3) + "| Processing file " + location.getName());

            try {
                FileInputStream fis = new FileInputStream(folder);

                int nRead;

                while ((nRead = fis.read(fileBytes)) != -1) { messageDigest.update(fileBytes, 0, nRead); }

                String hexStr = Format.bytesToHexString(messageDigest.digest());

                SymSyncFile symSyncFile = new SymSyncFile(location.getName(), folder, location.length(), hexStr, fileBytes);

                logger.info( "Publishing file: " + location.getName() + " | " + hexStr);

                symSyncFileMessageQueue.appendMessage(symbiosisUserId, symSyncFile);

                userFiles.get(symbiosisUserId).add(symSyncFile);

                logger.info( "File published: " + location.getName());
            }
            catch (Exception ex) {
                logger.severe("Failed to process file " + folder + ": " + ex.getMessage());
            }
        }
        else
        {
            logger.warning("Skipping file: " + folder);
        }
    }

}
