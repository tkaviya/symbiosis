package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_common.utilities.Format;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.jms.*;
import java.util.Enumeration;

public class SymSyncServerTest extends SymSyncServer implements ExceptionListener
{
	@BeforeClass
	public void setUp() throws Exception
	{
	}

	@AfterClass
	public void tearDown() throws Exception
	{
		apacheMQueSession.close();
		apacheMQueueConnection.close();
	}

	@Test
	public void testGetSyncList()
	{
		System.out.println("Getting sync list");
		try
		{
			// Create a ConnectionFactory
			System.out.println("Starting consumer apache MQueue connection factory");
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

			// Create a Connection
			System.out.println("Creating consumer apache MQueue connection");
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			System.out.println("Creating consumer apache MQueue session");
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			System.out.println("Creating consumer apache MQueue destination");
			Destination destination = session.createQueue("SYM_SYNC_SERVER.SERVER");

			// Create a MessageConsumer from the Session to the Topic or Queue
			System.out.println("Creating consumer apache MQueue consumer");
			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message
			System.out.println("Getting consumer queued messages");

			Message message = consumer.receive(5000);

			while (message != null)
			{
				System.out.println("Got JMS message: " + message.getJMSMessageID());

				Enumeration<String> propertyNames = message.getPropertyNames();

				System.out.println("Getting consumer queued message data");

				while(propertyNames.hasMoreElements())
				{
					String currentElement = propertyNames.nextElement();

					System.out.println("Reading property: " + currentElement);
					String data = message.getStringProperty(currentElement);

					if (data != null)
					{
						Object dataObject = Format.objectFromBase64(data);

						if (dataObject instanceof SymSyncFile)
						{
							SymSyncFile symSyncFileData = (SymSyncFile) dataObject;
							System.out.println("File Name: " + symSyncFileData.getFileName());
							System.out.println("File Path: " + symSyncFileData.getFilePath());
							System.out.println("File Size: " + symSyncFileData.getSize());
							System.out.println("Checksum:  " + symSyncFileData.getFileChecksum());
						}
						else
						{
							System.out.println("Got mangled data for property: " + currentElement);
						}
					}
					else
					{
						System.out.println("Got no data for property: " + currentElement);
					}
				}

				message = consumer.receive(5000);
			}

			System.out.println("No more messages in queue");

			consumer.close();
			session.close();
			connection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception occured: " + e.getMessage());
		}
	}

	public synchronized void onException(JMSException ex)
	{
		System.out.println("Exception occured: " + ex.getMessage());
	}

	@Test
	public void testGetFile() throws Exception
	{

	}

	@Test
	public void testPutFile() throws Exception
	{

	}
}