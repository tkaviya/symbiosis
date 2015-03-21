package net.blaklizt.symbiosis.sym_sync;

import com.google.gson.Gson;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 5:24 AM
 */
class SymSyncServer {
	Logger logger = Configuration.getNewLogger(SymSyncServer.class.getSimpleName());

	SymSyncServer() {}

	String getFileHashes()
	{
		logger.info("Getting all files to sync");
		Gson responseData = new Gson();
		HashMap<String, String> fileHasheResponse = new HashMap<>();



		try
		{
			fileHasheResponse.put("response_code","0");
			fileHasheResponse.put("response_message","Success");

			String folders = Configuration.getProperty("sync", "locations");
			String[] folderData = folders.split("|");
			for (String folder : folderData)
			{
				String[] folderInfo = folder.split(",");
				String folderName = folderInfo[0];
				boolean recursive = Boolean.parseBoolean(folderInfo[1]);
			}

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

	public void run() {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("TEST.FOO");

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
			TextMessage message = session.createTextMessage(text);

			// Tell the producer to send the message
			System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
			producer.send(message);

			// Clean up
			session.close();
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}
}
