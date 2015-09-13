package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Format;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 5:24 AM
 */
@Service
class SymSyncServer {

	protected static final Logger logger = Configuration.getNewLogger(SymSyncServer.class.getSimpleName());

	protected static Session apacheMQueSession;

	protected static MessageProducer syncQueue;

	protected static Connection apacheMQueueConnection;

	protected static MessageDigest messageDigest;

	protected static final int MAX_FILES = 1;

	protected static int countProcessed = 0;

	protected static SymSyncServer symSyncServer = null;

	protected static boolean serverStarted = false;

	protected static byte[] fileBytes = new byte[1024];

	protected static final short SYNC_SERVER_CONSTANT = 6767;

	//singleton
	public static SymSyncServer getInstance()
	{
		if (symSyncServer == null)
		{
			symSyncServer = new SymSyncServer();
		}
		return symSyncServer;
	}

	//singleton
	protected SymSyncServer()
	{
		if (!serverStarted && startMQueue())
		{
			logger.info("Apache MQueue started. Populating consumer data");
			getFileHashes();
		}
		else if (serverStarted)
		{
			logger.info("Server already running");
		}
		else
		{
			logger.info("Failed to start Apache MQueue. No data will be added to the queue");
		}
	}

	private void getFileHashes()
	{
		logger.info("Getting all files to sync");

		try
		{
			messageDigest = MessageDigest.getInstance("SHA-512");

			String allFolderData = Configuration.getOSProperty("sync", "locations");
			logger.info("Got all folder data: " + allFolderData);
			String[] folderData = allFolderData.split("\\|");
			for (String folder : folderData)
			{
				logger.info("Processing entry: " + folder);
				String[] folderInfo = folder.split(",");

				if (folderInfo.length == 2)
				{
					try
					{
						//process folders recursively
						processFolder(folderInfo[0].trim(), !folderInfo[1].trim().equals("0"));
					}
					catch (Exception ex)
					{
						logger.severe("Invalid configuration specified: " + folder);
					}
				}
				else
				{
					logger.severe("Invalid configuration specified: " + folder);
					logger.info("folderInfo[0] = " + folderInfo[0]);
					logger.info("folderInfo[1] = " + folderInfo[1]);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

			logger.severe("Failed to get file data: " + ex.getMessage());
		}
	}

	private void processFolder(String full_path, boolean recursive)
	{
		if (countProcessed >= MAX_FILES) { logger.fine("Processing limit reached! Skipping " + full_path); return; }

		logger.info("Recursive = " + (recursive ? 1 : 0) + " | Processing path " + full_path);

		File location = new File(full_path);
		if (location.isDirectory() && !location.getName().equals(".") && !location.getName().equals("..") && recursive)
		{
			File[] files = location.listFiles();
			for (File file : files)
			{
				processFolder(file.getAbsolutePath(), recursive);
			}
		}
		else if (location.isFile() && !location.getName().equals(".") && !location.getName().equals(".."))
		{
			logger.info(CommonUtilities.alignStringToLength(String.valueOf(++countProcessed), 3) + "| Processing file " + location.getName());
			try
			{
				FileInputStream fis = new FileInputStream(full_path);
				int nread = 0;

				while ((nread = fis.read(fileBytes)) != -1) { messageDigest.update(fileBytes, 0, nread); }

				String hexStr = Format.bytesToHexString(messageDigest.digest());

				SymSyncFile symSyncFile = new SymSyncFile(location.getName(), full_path, location.length(), hexStr);

				String objectStr = Format.objectToBase64(symSyncFile);

				logger.info( "Publishing file: " + location.getName() + " | " + hexStr);
				logger.info( "Object Str     : " + objectStr);

				Message message = apacheMQueSession.createMapMessage();
				message.setStringProperty(hexStr, objectStr);

				syncQueue.send(message);
			}
			catch (Exception ex)
			{
				logger.severe("Failed to process file " + full_path + ": " + ex.getMessage());
			}

		}
		else
		{
			logger.warning("Skipping file: " + full_path);
		}
	}

	private boolean startMQueue()
	{
		serverStarted = true;
		try
		{
			// Create a ConnectionFactory
			logger.info("Starting publisher apache MQueue");
//			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://0.0.0.0");

			// Create a Connection
			logger.info("Creating publisher apache MQueue connection factory");
			apacheMQueueConnection = connectionFactory.createConnection();
			apacheMQueueConnection.start();

			// Create a Session
			logger.info("Creating publisher apache MQueue session");
			apacheMQueSession = apacheMQueueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			logger.info("Creating publisher apache MQueue destination");
			Destination destination = apacheMQueSession.createQueue("SYM_SYNC_SERVER.SERVER");

			// Create a MessageProducer from the Session to the Topic or Queue
			logger.info("Creating publisher apache MQueue message producer");
			syncQueue = apacheMQueSession.createProducer(destination);
			syncQueue.setDeliveryMode(DeliveryMode.PERSISTENT);

			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.severe("Failed to initialize publisher MQueue: " + ex.getMessage());
			return false;
		}
	}
}
