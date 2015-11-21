//
///******************************************************************************
// *                                                                            *
// *    Project:     ${PROJECT_NAME}                                                 *
// *    Created:     13 / 09 / 2015                                *
// *    Platform:    Red Hat Linux 9                                            *
// *    Author:      Tich de Blak (Tsungai Kaviya)                              *
// *    Copyright:   Blaklizt Entertainment                                     *
// *    Website:     http://www.blaklizt.net                                    *
// *    Contact:     blaklizt@gmail.com                                         *
// *                                                                            *
// *    This program is free software; you can redistribute it and/or modify    *
// *    it under the terms of the GNU General Public License as published by    *
// *    the Free Software Foundation; either version 2 of the License, or       *
// *    (at your option) any later version.                                     *
// *                                                                            *
// *    This program is distributed in the hope that it will be useful,         *
// *    but WITHOUT ANY WARRANTY; without even the implied warranty of          *
// *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
// *    GNU General Public License for more details.                            *
// *                                                                            *
// ******************************************************************************/
//
//package net.blaklizt.symbiosis.sym_sync.server.file;
//
//import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserOptionDao;
//import net.blaklizt.symbiosis.sym_persistence.entity.simple_type.symbiosis_user_option;
//import net.blaklizt.symbiosis.sym_persistence.helper.OptionHelper;
//import net.blaklizt.symbiosis.sym_sync.server.SymFileProcessorService;
//import net.blaklizt.symbiosis.sym_sync.server.queue.SymSyncDataQueue;
//import net.blaklizt.symbiosis.sym_sync.api.SymSyncResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedList;
//import java.util.logging.Logger;
//
//import static java.lang.String.format;
//
//@Service
//public class GeneralFileProcessorServiceImpl implements SymFileProcessorService {
//
//    private static final Logger logger = Configuration.getNewLogger(GeneralFileProcessorServiceImpl.class.getSimpleName());
//
//    @Autowired SymbiosisUserOptionDao symbiosisUserOptionDao;
//
//    @Autowired SymSyncDataQueue symSyncDataQueue;
//
//    @Override
//    public boolean prepareFileList(Long symbiosisUserId) {
//
//        logger.info("Checking for files to sync for user " + symbiosisUserId);
//
//        symbiosis_user_option syncFolderLocation = symbiosisUserOptionDao.findByUserIDAndOption(
//                symbiosisUserId, OptionHelper.SYNC_FOLDER.value());
//
//        if (syncFolderLocation != null) {
//            logger.info("File list folder = " + syncFolderLocation.getOption_value());
//            symSyncDataQueue.prepareFileQueue(symbiosisUserId, syncFolderLocation.getOption_value(), true);
//            return true;
//        }
//        else {
//            logger.warning(format("User preference %s was not found for user %s.", OptionHelper.SYNC_FOLDER.name(), symbiosisUserId));
//            return false;
//        }
//    }
//
//    @Override
//    public boolean processAndPersist(LinkedList<SymSyncResource> fileList) {
//        return false;
//    }
//}
