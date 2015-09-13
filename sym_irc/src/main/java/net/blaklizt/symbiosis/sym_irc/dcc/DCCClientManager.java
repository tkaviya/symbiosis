package net.blaklizt.symbiosis.sym_irc.dcc;

import net.blaklizt.symbiosis.sym_irc.ResponseMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 12/1/13
 * Time: 6:22 PM
 */
public class DCCClientManager extends Observable implements Runnable
{
	private static final Logger log4j = Logger.getLogger(DCCClientManager.class.getSimpleName());
	protected String nick;
	protected String remoteHost;
	protected short remotePort;

	public DCCClientManager(String nick, String remoteHost, short remotePort)
	{
		this.nick = nick;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
	}

	@Override public void notifyObservers(Object arg) { setChanged(); super.notifyObservers(arg); }

	@Override
	public void run()
	{
		try
		{
			InetSocketAddress socketAddress = new InetSocketAddress(remoteHost, remotePort);
			SocketChannel dccSocketChannel = SocketChannel.open(socketAddress);
			dccSocketChannel.configureBlocking(false);
			if (dccSocketChannel.isConnected())
			{
				//connection succeeded, get local hostname/IP
				String message = ">>> Connected to nick " + nick + " via DCC";
				notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.DCC, nick, message));
				log4j.info(message);

				int count;
				ByteBuffer buffer = ByteBuffer.allocate(10240);
				while ((count = dccSocketChannel.read(buffer)) > 0)
				{
					//receive
					String receivedStr = "";
					for (int c = 0; c < count; c++) receivedStr += (char)buffer.get(c);
					notifyObservers(new ResponseMessage(
							receivedStr, ResponseMessage.IRC_MSG_TYPE.DCC,
							receivedStr.split(" ")[0], receivedStr));
					log4j.info("DCC: " + receivedStr);
					buffer.clear();
				}
			}
		}
		catch (UnresolvedAddressException u)
		{
			String message = "!!! Failed to resolve host " + remoteHost + ". " + u.getMessage();
			notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.DCC, nick, message));
			log4j.info(message);
		}
		catch (IOException e)
		{
			String message = "!!! Failed to connect to " + remoteHost + ":" + remotePort;
			notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.DCC, nick, message));
			log4j.info(message);
		}
	}

}
