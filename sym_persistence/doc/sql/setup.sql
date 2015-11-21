create database if not exists symbiosis;use symbiosis;grant all on symbiosis.* to 'symbiosis_admin'@'localhost' identified by 'symADM1NP@$$';drop table if exists symbiosis_group;create table if not exists symbiosis_group(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  description VARCHAR(20),  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_role;create table if not exists symbiosis_role(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  description VARCHAR(20),  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_system;create table if not exists symbiosis_system(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  description VARCHAR(128) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_group_system_role;create table if not exists symbiosis_group_system_role(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  symbiosis_group_id BIGINT(20) NOT NULL REFERENCES symbiosis_group(id) ON UPDATE CASCADE,  symbiosis_system_id BIGINT(20) REFERENCES symbiosis_system(id) ON UPDATE CASCADE,  symbiosis_role_id BIGINT(20) NOT NULL REFERENCES symbiosis_role(id) ON UPDATE CASCADE,  description VARCHAR(20) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_channel;create table if not exists symbiosis_channel(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  description VARCHAR(50) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_user_status;create table if not exists symbiosis_user_status(  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,  description VARCHAR(50) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_option;create table if not exists symbiosis_option(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  description VARCHAR(50) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_country;create table if not exists symbiosis_country(  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,  country_name VARCHAR(50) NOT NULL,  country_code_prefix BIGINT(20) NOT NULL);drop table if exists symbiosis_language;create table if not exists symbiosis_language(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  description VARCHAR(50) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_user;create table if not exists symbiosis_user(	id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  first_name VARCHAR(50),  last_name VARCHAR(50),  user_name VARCHAR(20) NOT NULL,  email VARCHAR(50),  msisdn VARCHAR(15),  password VARCHAR(256) NOT NULL,  salt VARCHAR(50) NOT NULL,	symbiosis_group_id BIGINT(20) NOT NULL REFERENCES symbiosis_group(id) ON UPDATE CASCADE,  symbiosis_country_id BIGINT(20) REFERENCES symbiosis_country(id) ON UPDATE CASCADE,  symbiosis_language_id BIGINT(20) REFERENCES symbiosis_language(id) ON UPDATE CASCADE,	auth_token VARCHAR(256));drop table if exists symbiosis_auth_user;create table if not exists symbiosis_auth_user(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  symbiosis_user_id BIGINT(19) NOT NULL REFERENCES symbiosis_user(id) ON UPDATE CASCADE,  symbiosis_channel_id BIGINT(20) NOT NULL REFERENCES symbiosis_channel(id) ON UPDATE CASCADE,  symbiosis_user_status_id BIGINT(20) NOT NULL REFERENCES symbiosis_user_status(id) ON UPDATE CASCADE,  device_id VARCHAR(50) COMMENT 'IMEI',  access_system_id VARCHAR(50) COMMENT 'IMSI/BrowserId/Sessionid',  registration_date DATETIME,  last_auth_date DATETIME,  last_login_date DATETIME);drop table if exists symbiosis_event_type;create table if not exists symbiosis_event_type(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  description VARCHAR(50) NOT NULL,  enabled TINYINT(4) NOT NULL DEFAULT 1);drop table if exists symbiosis_event_log;create table if not exists symbiosis_event_log(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  symbiosis_event_type_id BIGINT(19) REFERENCES symbiosis_event_type(id) ON UPDATE CASCADE,  event_date DATETIME NOT NULL,  symbiosis_user_id BIGINT(19) NOT NULL REFERENCES symbiosis_user(id) ON UPDATE CASCADE,  description VARCHAR(256) NOT NULL);create table if not exists symbiosis_user_option(  id BIGINT(19) AUTO_INCREMENT PRIMARY KEY,  symbiosis_user_id BIGINT(19) NOT NULL REFERENCES symbiosis_user(id) ON UPDATE CASCADE,  symbiosis_option_id BIGINT(19) NOT NULL REFERENCES symbiosis_option(id) ON UPDATE CASCADE,  option_value VARCHAR(256) NOT NULL);insert into symbiosis_channel (description, enabled) values ('ANDROID',1);insert into symbiosis_user_status (description, enabled) values ('INACTIVE',1);insert into symbiosis_user_status (description, enabled) values ('ACTIVE',1);insert into symbiosis_user_status (description, enabled) values ('PENDING',1);insert into symbiosis_user_status (description, enabled) values ('SUSPENDED',1);insert into symbiosis_user_status (description, enabled) values ('BLOCKED',1);insert into symbiosis_user_status (description, enabled) values ('UNKNOWN',1);insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_ADMIN',1);insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_EDITOR',0);insert into symbiosis_group (description, enabled) values ('SYMBIOSIS_USER',0);insert into symbiosis_group (description, enabled) values ('STREETS_ADMIN',0);insert into symbiosis_group (description, enabled) values ('STREETS_EDITOR',0);insert into symbiosis_group (description, enabled) values ('STREETS_USER',0);insert into symbiosis_system (description, enabled) values ('SYMBIOSIS',1);insert into symbiosis_system (description, enabled) values ('STREETS',0);insert into symbiosis_system (description, enabled) values ('SYNC',1);insert into symbiosis_role (description, enabled) values ('ROLE_ADMIN', 1);insert into symbiosis_role (description, enabled) values ('ROLE_EDITOR', 1);insert into symbiosis_role (description, enabled) values ('ROLE_USER', 1);insert into symbiosis_option (description, enabled) values ('SYNC_FOLDER',1);insert into symbiosis_option (description, enabled) values ('SYNC_TYPE',1);insert into symbiosis_group_system_role (symbiosis_group_id, symbiosis_system_id, symbiosis_role_id, description, enabled)    select sg.id, ss.id, sr.id, 'SYMBIOSIS_GLOBAL_ADMIN', 1    from symbiosis_group sg, symbiosis_system ss, symbiosis_role sr    where sg.description = 'SYMBIOSIS_ADMIN'      and ss.description = 'SYMBIOSIS'      and sr.description = 'ROLE_ADMIN';insert into symbiosis_user_option (symbiosis_user_id, symbiosis_option_id, option_Value)    select su.id, so.id, 'K:\\music'    from symbiosis_user su, symbiosis_option so    where su.user_name = 'tkaviya' and so.description = 'SYNC_FOLDER';insert into symbiosis_user_option (symbiosis_user_id, symbiosis_option_id, option_Value)  select su.id, so.id, 'mp3'  from symbiosis_user su, symbiosis_option so  where su.user_name = 'tkaviya' and so.description = 'SYNC_TYPE';insert into symbiosis_user(first_name,last_name,user_name,email,msisdn,                           password,salt,                           symbiosis_group_id,symbiosis_country_id,symbiosis_language_id,auth_token)  values ('Tsungai','Kaviya','tkaviya','bhpenetrator@gmail.com','0813747410',          '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25','',          0, 0, 0, NULL);insert into symbiosis_auth_user (symbiosis_user_id,symbiosis_channel_id,symbiosis_user_status_id,device_id,                                 access_system_id,registration_date,last_auth_date,last_login_date)values (1,1,1,null,        null,NULL,NULL,NULL);