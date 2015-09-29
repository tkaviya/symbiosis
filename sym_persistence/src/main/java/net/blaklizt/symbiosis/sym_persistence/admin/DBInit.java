package net.blaklizt.symbiosis.sym_persistence.admin;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;
import net.blaklizt.symbiosis.sym_persistence.entity.simple_type.symbiosis_user_option;
import net.blaklizt.symbiosis.sym_persistence.helper.DaoManager;
import org.springframework.stereotype.Service;

/******************************************************************************
 * *
 * Created:     29 / 09 / 2015                                             *
 * Platform:    Red Hat Linux 9                                            *
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
 ******************************************************************************/

@Service
public class DBInit {

    static DBInit dbInit = null;

    public static DBInit getInstance() {
        if (dbInit == null) dbInit = new DBInit();
        return dbInit;
    }

    private DBInit()
    {

        //setup options
        symbiosis_option sync_folder = (symbiosis_option)new symbiosis_option().setValues("SYNC_FOLDER", true);
        symbiosis_option sync_type = (symbiosis_option)new symbiosis_option().setValues("SYNC_TYPE", true);

        //setup channel
        symbiosis_channel android = (symbiosis_channel)new symbiosis_channel().setValues("ANDROID", true);


        /* insert symbiosis configuration data into database */
        DaoManager.getInstance().getOptionDao().save(sync_folder);
        DaoManager.getInstance().getOptionDao().save(sync_type);
        DaoManager.getInstance().getChannelDao().save(android);


        symbiosis_user_option default_options = new symbiosis_user_option();
        default_options.setSymbiosis_user_id(0L);
        default_options.setSymbiosis_option_id(sync_folder.getId());
        default_options.setOption_value("G:\\Promo");

        DaoManager.getInstance().getUserOptionDao().save(default_options);
    }
}
