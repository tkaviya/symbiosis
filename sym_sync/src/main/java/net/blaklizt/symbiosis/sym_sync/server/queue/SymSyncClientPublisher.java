package net.blaklizt.symbiosis.sym_sync.server.queue;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.utilities.Format;
import net.blaklizt.symbiosis.sym_sync.server.file.SymSyncFile;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import static java.lang.String.format;

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
public class SymSyncClientPublisher implements ExceptionListener {

    @Autowired SymSyncFileMessageQueue symSyncFileMessageQueue;

    private static final Logger logger = Configuration.getNewLogger(SymSyncFileDemandQueue.class.getSimpleName());

    private ActiveMQConnectionFactory connectionFactory;

    private Connection connection;

    private static Session session;

    //unique destinations per user
    private static HashMap<Long, MessageConsumer> userConsumers = new HashMap<>();

    private SymSyncClientPublisher() {

        try
        {
            // Create a ConnectionFactory
            logger.info("Starting consumer apache MQueue connection factory");
            connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

            logger.info("Creating consumer apache MQueue connection");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Connection
            connection.setExceptionListener(this);

            // Create a Session
            logger.info("Creating consumer apache MQueue session");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.info("Exception occurred: " + e.getMessage());
        }
    }

    public static MessageConsumer getMessageConsumer(Long symbiosisUserId) {
        try
        {
            MessageConsumer userSyncQueue = userConsumers.get(symbiosisUserId);
            if (userSyncQueue == null)
            {
                logger.info(format("Creating consumer MQueue for userID %d", symbiosisUserId));
                userSyncQueue = session.createConsumer(SymSyncFileMessageQueue.getUserDataDestination(session, symbiosisUserId));
                userConsumers.put(symbiosisUserId, userSyncQueue);
            }
            return userSyncQueue;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public LinkedList<SymSyncFile> getFiles(Long symbiosisUserId)
    {
        LinkedList<SymSyncFile> syncFiles = new LinkedList<>();
        try
        {
            MessageConsumer consumer = getMessageConsumer(symbiosisUserId);

            // Wait for a message
            logger.info("Getting consumer queued messages");

            Message message = consumer.receive(5000);

            while (message != null)
            {
                logger.info("Got JMS message: " + message.getJMSMessageID());

                Enumeration<String> propertyNames = message.getPropertyNames();

                logger.info("Getting consumer queued message data");

                while (propertyNames.hasMoreElements()) {
                    String currentElement = propertyNames.nextElement();

                    logger.info("Reading property: " + currentElement);
                    String data = message.getStringProperty(currentElement);

                    if (data != null) {
                        Object dataObject = Format.objectFromBase64(data);

                        if (dataObject instanceof SymSyncFile) {
                            SymSyncFile symSyncFileData = (SymSyncFile) dataObject;
                            logger.info("File Name: " + symSyncFileData.getFileName());
                            logger.info("File Path: " + symSyncFileData.getFilePath());
                            logger.info("File Size: " + symSyncFileData.getSize());
                            logger.info("Checksum:  " + symSyncFileData.getFileChecksum());
                            syncFiles.add(symSyncFileData);
                        } else {
                            logger.info("Got mangled data for property: " + currentElement);
                        }
                    } else {
                        logger.info("Got no data for property: " + currentElement);
                    }
                }
                message = consumer.receive(5000);
            }

            return syncFiles;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public synchronized void onException(JMSException ex)
    {
        System.out.println("Exception occured: " + ex.getMessage());
    }
}
