package net.blaklizt.symbiosis.sym_persistence.admin;

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

//    static DBInit dbInit = null;
//
//    public static DBInit getInstance() {
//        if (dbInit == null) dbInit = new DBInit();
//        return dbInit;
//    }
//
//    private DBInit()
//    {
//
//        //setup options
//        symbiosis_option sync_folder = (symbiosis_option)new symbiosis_option().setValues("SYNC_FOLDER", true);
//        symbiosis_option sync_type = (symbiosis_option)new symbiosis_option().setValues("SYNC_TYPE", true);
//
//        //setup channel
//        symbiosis_channel android = (symbiosis_channel)new symbiosis_channel().setValues("ANDROID", true);
//
//
//        /* insert symbiosis configuration data into database */
//        DaoManager.getInstance().getOptionDao().save(sync_folder);
//        DaoManager.getInstance().getOptionDao().save(sync_type);
//        DaoManager.getInstance().getChannelDao().save(android);
//
//
//        symbiosis_user_option default_options = new symbiosis_user_option();
//        default_options.setSymbiosis_user_id(0L);
//        default_options.setSymbiosis_option_id(sync_folder.getId());
//        default_options.setOption_value(!System.getProperty("os.name").equalsIgnoreCase("win") ? "/mnt/archive/Promo" : "G:\\Promo");
//
//        DaoManager.getInstance().getUserOptionDao().save(default_options);
//    }

	/**
	 insert into symbiosis_channel (description, enabled) values ('ANDROID',1);

	 insert into symbiosis_user_status (description, enabled) values ('ACC_INACTIVE',1);
	 insert into symbiosis_user_status (description, enabled) values ('ACC_ACTIVE',1);
	 insert into symbiosis_user_status (description, enabled) values ('PENDING',1);
	 insert into symbiosis_user_status (description, enabled) values ('ACC_SUSPENDED',1);
	 insert into symbiosis_user_status (description, enabled) values ('BLOCKED',1);
	 insert into symbiosis_user_status (description, enabled) values ('UNKNOWN',1);

	 insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_ADMIN',1);
	 insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_EDITOR',0);
	 insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_USER',0);
	 insert into symbiosis_group (description, enabled) values ('STREETS_ADMIN',0);
	 insert into symbiosis_group (description, enabled) values ('STREETS_EDITOR',0);
	 insert into symbiosis_group (description, enabled) values ('STREETS_USER',0);

	 insert into symbiosis_system (description, enabled) values ('SYMBIOSIS',1);
	 insert into symbiosis_system (description, enabled) values ('STREETS',0);
	 insert into symbiosis_system (description, enabled) values ('SYNC',1);

	 insert into symbiosis_role (description, enabled) values ('ROLE_ADMIN', 1);
	 insert into symbiosis_role (description, enabled) values ('ROLE_EDITOR', 1);
	 insert into symbiosis_role (description, enabled) values ('ROLE_USER', 1);

	 insert into symbiosis_option (description, enabled) values ('SYNC_FOLDER',1);
	 insert into symbiosis_option (description, enabled) values ('SYNC_TYPE',1);

	 insert into symbiosis_group_system_role (symbiosis_group_id, symbiosis_system_id, symbiosis_role_id, description, enabled)
	 select sg.id, ss.id, sr.id, 'SYMBIOSIS_GLOBAL_ADMIN', 1
	 from symbiosis_group sg, symbiosis_system ss, symbiosis_role sr
	 where sg.description = 'SYMBIOSIS_ADMIN'
	 and ss.description = 'SYMBIOSIS'
	 and sr.description = 'ROLE_ADMIN';

	 insert into symbiosis_user_option (symbiosis_user_id, symbiosis_option_id, option_Value)
	 select su.id, so.id, 'K:\\music'
	 from symbiosis_user su, symbiosis_option so
	 where su.user_name = 'tkaviya' and so.description = 'SYNC_FOLDER';

	 insert into symbiosis_user_option (symbiosis_user_id, symbiosis_option_id, option_Value)
	 select su.id, so.id, 'mp3'
	 from symbiosis_user su, symbiosis_option so
	 where su.user_name = 'tkaviya' and so.description = 'SYNC_TYPE';

	 insert into symbiosis_user(first_name,last_name,user_name,email,msisdn,
	 password,salt,
	 symbiosis_group_id,symbiosis_country_id,symbiosis_language_id,auth_token)
	 values ('Tsungai','Kaviya','tkaviya','bhpenetrator@gmail.com','0813747410',
	 '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25','',
	 0, 0, 0, NULL);

	 insert into symbiosis_auth_user (symbiosis_user_id,symbiosis_channel_id,symbiosis_user_status_id,device_id,
	 access_system_id,registration_date,last_auth_date,last_login_date)
	 values (1,1,1,null,
	 null,NULL,NULL,NULL);
	*/
}
