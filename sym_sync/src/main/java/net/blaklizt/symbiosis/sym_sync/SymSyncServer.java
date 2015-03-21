package net.blaklizt.symbiosis.sym_sync;

import com.google.gson.Gson;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 5:24 AM
 */
class SymSyncServer {

	private static final Logger logger = Configuration.getNewLogger(SymSyncServer.class.getSimpleName());

	private static Session apacheMQueSession;

	private static MessageProducer syncQueue;

	private static Connection apacheMQueueConnection;

	private static MessageDigest md5Digest;

	SymSyncServer()
	{
		if (startMQueue())
		{
			logger.info("Apache MQueue started. Populating consumer data");
			getFileHashes();
		}
		else
		{
			logger.info("Failed to start Apache MQueue. No data will be added to the queue");
		}
	}

	String getFileHashes()
	{
		logger.info("Getting all files to sync");
		Gson responseData = new Gson();
		HashMap<String, String> fileHasheResponse = new HashMap<>();

		try
		{
			fileHasheResponse.put("response_code","0");
			fileHasheResponse.put("response_message","Success");
			md5Digest = MessageDigest.getInstance("MD5");

			String allFolderData = Configuration.getProperty("sync", "locations");
			String[] folderData = allFolderData.split("|");
			for (String folder : folderData)
			{
				String[] folderInfo = folder.split(",");
				processFolder(folderInfo[0], Boolean.parseBoolean(folderInfo[1]));
			}

			// Clean up
			apacheMQueSession.close();
			apacheMQueueConnection.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

			fileHasheResponse.put("response_code", String.valueOf(ResponseCode.SUCCESS));
			fileHasheResponse.put("response_message", ResponseCode.SUCCESS.responseMsg());

			logger.error("Failed to get file data: " + ex.getMessage());
		}

		return responseData.toJson(fileHasheResponse);
	}

	void processFolder(String path, boolean recursive)
	{
		logger.error("Recursive = " + (recursive ? 1 : 0) +  " | Processing path " + path);
		File location = new File(path);
		if (location.isDirectory() && recursive) { processFolder(path, recursive); }
		else if (location.isFile())
		{
			logger.info("Processing file " + location.getName());
			try (InputStream is = Files.newInputStream(Paths.get(path)))
			{
				new DigestInputStream(is, md5Digest);
				String digest = String.valueOf(md5Digest.digest());

				logger.info("Publishing file: " + location.getName() + " | " + digest);

				Message message = apacheMQueSession.createMapMessage();
				message.setObjectProperty(digest, new SymSyncFile(location.getName(), location.getAbsolutePath(), location.length(), digest));

				syncQueue.send(message);
			}
			catch (Exception ex)
			{
				logger.error("Failed to read file " + path + ": " + ex.getMessage());
			}

		}
	}

	public boolean startMQueue()
	{
		try
		{
			// Create a ConnectionFactory
			logger.info("Starting apache MQueue");
//			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://0.0.0.0");

			// Create a Connection
			logger.info("Creating apache MQueue connection factory");
			apacheMQueueConnection = connectionFactory.createConnection();
			apacheMQueueConnection.start();

			// Create a Session
			logger.info("Creating apache MQueue session");
			apacheMQueSession = apacheMQueueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			logger.info("Creating apache MQueue destination");
			Destination destination = apacheMQueSession.createQueue("SYM_SYNC_SERVER.SERVER");

			// Create a MessageProducer from the Session to the Topic or Queue
			logger.info("Creating apache MQueue message producer");
			syncQueue = apacheMQueSession.createProducer(destination);
			syncQueue.setDeliveryMode(DeliveryMode.PERSISTENT);

			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error("Failed to initialize MQueue: " + ex.getMessage());
			return false;
		}
	}
}
