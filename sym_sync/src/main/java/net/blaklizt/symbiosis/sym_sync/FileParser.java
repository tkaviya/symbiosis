package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Format;

import javax.jms.Message;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/******************************************************************************
 *                                                                            *
 *    Project:     ${PROJECT_NAME}                                            *
 *    Created:     13 / 09 / 2015                                            *
 *    Platform:    Red Hat Linux 9                                            *
 *    Author:      Tich de Blak (Tsungai Kaviya)                              *
 *    Copyright:   BlakLizt Entertainment                                     *
 *    Website:     http://www.blaklizt.net                                    *
 *    Contact:     blaklizt@gmail.com                                         *
 *                                                                            *
 *    This program is free software; you can redistribute it and/or modify    *
 *    it under the terms of the GNU General Public License as published by    *
 *    the Free Software Foundation; either version 2 of the License, or       *
 *    (at your option) any later version.                                     *
 *                                                                            *
 *    This program is distributed in the hope that it will be useful,         *
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 *    GNU General Public License for more details.                            *
 *                                                                            *
 ******************************************************************************/
public class FileParser implements Runnable {

    protected static final Logger logger = Configuration.getNewLogger(SymSyncServer.class.getSimpleName());


    protected static final int MAX_FILES = 1000;

    protected static MessageDigest messageDigest;
    static { try { messageDigest = MessageDigest.getInstance("SHA-512"); }
    catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
    }
    protected byte[] fileBytes = new byte[1024];

    protected int countProcessed = 0;

    private String fullPath;

    private boolean recursive;

    private Long symbiosisUserId;

    public FileParser(Long symbiosisUserId, String fullPath, boolean recursive) {
        this.symbiosisUserId = symbiosisUserId;
        this.fullPath = fullPath;
        this.recursive = recursive;
    }

    @Override
    public void run()
    {
        if (countProcessed >= MAX_FILES) { logger.fine("Processing limit reached! Skipping " + fullPath); return; }

        logger.info("Recursive = " + (recursive ? 1 : 0) + " | Processing path " + fullPath);

        File location = new File(fullPath);
        if (location.isDirectory() && !location.getName().equals(".") && !location.getName().equals("..") && recursive)
        {
            File[] files = location.listFiles();
            for (File file : files)
            {
                fullPath = file.getAbsolutePath();
                run();
            }
        }
        else if (location.isFile() && !location.getName().equals(".") && !location.getName().equals(".."))
        {
            logger.info(CommonUtilities.alignStringToLength(String.valueOf(++countProcessed), 3) + "| Processing file " + location.getName());
            try
            {
                FileInputStream fis = new FileInputStream(fullPath);

                int nRead;

                while ((nRead = fis.read(fileBytes)) != -1) { messageDigest.update(fileBytes, 0, nRead); }

                String hexStr = Format.bytesToHexString(messageDigest.digest());

                SymSyncFile symSyncFile = new SymSyncFile(location.getName(), fullPath, location.length(), hexStr);

                String objectStr = Format.objectToBase64(symSyncFile);

                logger.info( "Publishing file: " + location.getName() + " | " + hexStr);
                logger.info( "Object Str     : " + objectStr);

                Message message = SymSyncServer.apacheMQueSession.createMapMessage();
                message.setStringProperty(hexStr, objectStr);

                SymSyncServer.syncQueue.send(message);
            }
            catch (Exception ex)
            {
                logger.severe("Failed to process file " + fullPath + ": " + ex.getMessage());
            }

        }
        else
        {
            logger.warning("Skipping file: " + fullPath);
        }
    }


}
