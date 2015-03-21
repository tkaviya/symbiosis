package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_common.utilities.Format;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.util.Enumeration;

public class SymSyncServerTest extends SymSyncServer implements ExceptionListener
{
	@Before
	public void setUp() throws Exception
	{
	}

	@After
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
			System.out.println("Getting consumer queued message");
			Message message = consumer.receive();

			Enumeration<String> propertyNames = message.getPropertyNames();

			System.out.println("Getting consumer queued message");

			while(propertyNames.hasMoreElements())
			{
				String currentElement = propertyNames.nextElement();
				System.out.println("Reading property: " + currentElement);
				String data = message.getStringProperty(currentElement);

				System.out.println("Object concat value : " + data);
				System.out.println("Object cast value   : " + data);
				System.out.println("String.valueOf value: " + String.valueOf(data));
				System.out.println("new String value    : " + new String(data.getBytes()));


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