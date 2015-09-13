
/******************************************************************************
 *                                                                            *
 *    Project:     ${PROJECT_NAME}                                                 *
 *    Created:     13 / 09 / 2015                                *
 *    Platform:    Red Hat Linux 9                                            *
 *    Author:      Tich de Blak (Tsungai Kaviya)                              *
 *    Copyright:   Blaklizt Entertainment                                     *
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

package net.blaklizt.symbiosis.sym_sync.service.impl;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUserOption;
import net.blaklizt.symbiosis.sym_persistence.dao.AbstractDao;
import net.blaklizt.symbiosis.sym_persistence.dao.DaoManager;
import net.blaklizt.symbiosis.sym_persistence.helper.OptionHelper;
import net.blaklizt.symbiosis.sym_persistence.helper.TypeHelper;
import net.blaklizt.symbiosis.sym_sync.SymSyncServer;
import net.blaklizt.symbiosis.sym_sync.service.SymSyncService;
import org.springframework.stereotype.Service;

@Service
public class SymSyncServiceImpl implements SymSyncService {

    @Override
    public void getFileList(Long symbiosisUserId) {

        Long id = TypeHelper.getIdByDescription((AbstractDao) DaoManager.getInstance().getOptionDao(), OptionHelper.SYNC_FOLDER);

        SymbiosisUserOption symbiosisUserOption = DaoManager.getInstance().getUserOptionDao().findByUserIDAndOption(symbiosisUserId, id);

        SymSyncServer.getInstance().getFileHashes(symbiosisUserId, symbiosisUserOption.getOptionValue(), true);
    }
}
