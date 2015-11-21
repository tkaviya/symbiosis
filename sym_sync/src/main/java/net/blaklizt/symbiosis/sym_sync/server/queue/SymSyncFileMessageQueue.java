package net.blaklizt.symbiosis.sym_sync.server.queue;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_sync.server.file.SymSyncFile;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 5:24 AM
 */

@Service
public class SymSyncFileMessageQueue {

	private static final Logger logger = Configuration.getNewLogger(SymSyncFileMessageQueue.class.getSimpleName());

	private static Session session;

	private static Connection apacheMQueueConnection;

	private static MessageProducer syncQueue;

	private static boolean serverStarted = false;

    //unique destinations per user
    private static HashMap<Long, MessageProducer> userProducers = new HashMap<>();

	protected SymSyncFileMessageQueue()
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
			session = apacheMQueueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			return true;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.severe("Failed to initialize publisher MQueue: " + ex.getMessage());
			return false;
		}
	}

    public static Destination getUserDataDestination(Session session, Long symbiosisUserId) {
        try {
//            return session.createQueue(format("SYM_SYNC_SERVER.%d", symbiosisUserId));
            return session.createQueue("SYM_SYNC_SERVER.SERVER");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static MessageProducer getMessageProducer(Long symbiosisUserId) {
        try
        {
            MessageProducer userSyncQueue = userProducers.get(symbiosisUserId);
            if (userSyncQueue == null)
            {
                userSyncQueue = session.createProducer(getUserDataDestination(session, symbiosisUserId));
                userSyncQueue.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                userProducers.put(symbiosisUserId, userSyncQueue);
            }
            return userSyncQueue;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

	public boolean appendMessage(Long symbiosisUserId, SymSyncFile symSyncFile) {
		try
		{
			Message message = session.createMapMessage();
			message.setStringProperty(symbiosisUserId + "_" + symSyncFile.getFileName(), symSyncFile.toBase64String());
            getMessageProducer(symbiosisUserId).send(message);
			return true;
		}
		catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}
}
