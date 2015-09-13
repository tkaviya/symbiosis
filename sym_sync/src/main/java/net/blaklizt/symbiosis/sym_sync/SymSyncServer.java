package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.configuration.ThreadPoolManager;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 5:24 AM
 */

public class SymSyncServer {

	protected static final Logger logger = Configuration.getNewLogger(SymSyncServer.class.getSimpleName());

	protected static Session apacheMQueSession;

	protected static SymSyncServer symSyncServer = null;

	public static Connection apacheMQueueConnection;

	public static MessageProducer syncQueue;

	protected static boolean serverStarted = false;

	public static SymSyncServer getInstance()
	{
		if (symSyncServer == null)
		{
			symSyncServer = new SymSyncServer();
		}
		return symSyncServer;
	}

	protected SymSyncServer()
	{
		if (!serverStarted && startMQueue())
		{
			logger.info("Apache MQueue started. Populating consumer data");
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

	public void getFileHashes(Long symbiosisUserId, String folder, boolean recursive)
	{
		logger.info(String.format("Processing files from %s | Recursive = %d", folder, recursive ? 1 : 0));
		ThreadPoolManager.schedule(new FileParser(symbiosisUserId, folder, recursive));
	}

	private boolean startMQueue()
	{
		serverStarted = true;
		try
		{
			// Create a ConnectionFactory
			logger.info("Starting publisher apache MQueue");
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
			syncQueue.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

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
