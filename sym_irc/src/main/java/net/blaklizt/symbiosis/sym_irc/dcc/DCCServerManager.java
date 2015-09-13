package net.blaklizt.symbiosis.sym_irc.dcc;

import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_irc.IRCClient;
import net.blaklizt.symbiosis.sym_irc.ResponseMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/29/13
 * Time: 12:50 PM
 */
public class DCCServerManager extends Observable implements Runnable
{
	protected static final Logger log4j = Logger.getLogger(DCCServerManager.class.getSimpleName());
	public static final String LOCALHOST = CommonUtilities.getConfiguration("irc", "dccBindAddress");
	public static final String LOCALPORT = CommonUtilities.getConfiguration("irc", "dccLocalPort");
	private static DCCServerManager dccServerManager;
	ServerSocketChannel acceptSocket;
	LinkedList<SocketChannel> dccSockets;
	Observer callbackManager;

	public DCCServerManager(ServerSocketChannel acceptSocket, Observer callbackManager)
	{
		try
		{
			dccServerManager = this;
			this.acceptSocket = acceptSocket;
			this.callbackManager = callbackManager;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	public static DCCServerManager getInstance(ServerSocketChannel acceptSocket, Observer callbackManager)
//	{
//		if (dccServerManager == null)
//		{
//			dccServerManager = new DCCServerManager(acceptSocket, callbackManager);
//		}
//		return dccServerManager;
//	}

	@Override
	protected void finalize() throws Throwable
	{
		try { if (acceptSocket != null) acceptSocket.close(); } catch (Throwable e) { e.printStackTrace(); }
		//try { if (dccSocket != null) dccSocket.close(); } catch (Throwable e) { e.printStackTrace(); }
		super.finalize();
	}

	@Override
	public void notifyObservers(Object arg) { setChanged(); super.notifyObservers(arg); }

	@Override
	public void run()
	{
		try
		{
			String message = ">>> Ready for DCC Connection.";
			notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.DCC, IRCClient.CLIENT, message));
			log4j.info(message);

			SocketChannel sock = acceptSocket.accept();

			int count;
			ByteBuffer buffer = ByteBuffer.allocate(10240);
			while ((count = sock.read(buffer)) > 0)
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
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
