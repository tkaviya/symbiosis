package net.blaklizt.symbiosis.sym_persistence;

/* *************************************************************************
 * Created:     2016/01/02                                                 *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *************************************************************************
*/

//
//@Configuration
//@EnableTransactionManagement
//@PropertySource({"classpath:properties/database/application.properties"})
//@ComponentScan({ "net.blaklizt.symbiosis.sym_persistence" })
//@ImportResource("classpath*:*-spring-context.xml")
//@EnableJpaRepositories(basePackages = "net.blaklizt.symbiosis.sym_persistence")
//@SpringBootApplication
//public class Main {
//	public static void main(String[] args) {
//		new ClassPathXmlApplicationContext("sym_persistence-spring-context.xml");
//
//		for (symbiosis_response_code response_code : SymbiosisConfig.getAllResponseCodes()) {
//			using(symbiosis_response_code.class).save(response_code);
//		}
//	}
//}