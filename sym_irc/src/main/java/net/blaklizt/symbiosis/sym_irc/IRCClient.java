package net.blaklizt.symbiosis.sym_irc;

import net.blaklizt.symbiosis.sym_irc.dcc.DCCServerManager;
import net.blaklizt.symbiosis.sym_irc.session.UserSession;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import static net.blaklizt.symbiosis.sym_common.configuration.Configuration.getProperty;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/26/13
 * Time: 5:10 PM
 */

public class IRCClient implements Observer
{
	private static final Logger logger = Logger.getLogger(IRCClient.class.getSimpleName());
	public static final String CLIENT = "CLIENT";
	protected ServerSocketChannel dccAcceptSocket;
	protected IRCServer currentServer;
	protected String currentChannel;
	protected Hashtable<String, Hashtable<String, LinkedList<String>>> serverResponses = new Hashtable<>();
	protected LinkedList<IRCServer> ircServers = new LinkedList<>();

	public IRCClient()
	{
		String[] servers = getProperty("irc", "SERVERS").split(",");

		try
		{
			//start DCC socket
			serverResponses.put(CLIENT, new Hashtable<String, LinkedList<String>>());
			serverResponses.get(CLIENT).put(CLIENT, new LinkedList<String>());

			String message = ">>> Initializing DCC socket on " +
							 DCCServerManager.LOCALHOST + ":" +
							 DCCServerManager.LOCALPORT;

			serverResponses.get(CLIENT).get(CLIENT).add(message);
			logger.info(message);
			dccAcceptSocket = ServerSocketChannel.open();
			dccAcceptSocket = dccAcceptSocket.bind(new InetSocketAddress(DCCServerManager.LOCALHOST,
					Integer.parseInt(DCCServerManager.LOCALPORT)));
			new Thread(new DCCServerManager(dccAcceptSocket, this)).start();
		}
		catch (Exception e)
		{
			String message = "!!! Failed to bind DCC socket on " +
							 DCCServerManager.LOCALHOST + ":" +
							 DCCServerManager.LOCALPORT;
			serverResponses.get(CLIENT).get(CLIENT).add(message);
			logger.severe(message);
		}

		for (String server : servers)
		{
			logger.info("Found configuration for server " + server);
			try
			{
				String[] serverOpts = getProperty("irc", server).split(",");
				IRCServer ircServer = new IRCServer(this, server,Short.parseShort(serverOpts[0]),serverOpts[1],serverOpts[2]);
				ircServer.addObserver(this); //listen in for irc events
				new Thread(ircServer).start();
				currentServer = ircServer;
				serverResponses.put(server, new Hashtable<String, LinkedList<String>>());
				serverResponses.get(server).put(server, new LinkedList<String>());
				ircServers.add(ircServer);
			}
			catch (NumberFormatException e) { e.printStackTrace(); }
		}
	}

	public IRCClient(UserSession userSession)
	{
		String[] servers = getProperty("irc", "SERVERS").split(",");

		try
		{
			//start DCC socket
			serverResponses.put(CLIENT, new Hashtable<String, LinkedList<String>>());
			serverResponses.get(CLIENT).put(CLIENT, new LinkedList<String>());

			String message = ">>> Initializing DCC socket on " +
							 DCCServerManager.LOCALHOST + ":" +
							 DCCServerManager.LOCALPORT;

			serverResponses.get(CLIENT).get(CLIENT).add(message);
			logger.info(message);
			dccAcceptSocket = ServerSocketChannel.open();
			dccAcceptSocket = dccAcceptSocket.bind(new InetSocketAddress(DCCServerManager.LOCALHOST,
					Integer.parseInt(DCCServerManager.LOCALPORT)));
			new Thread(new DCCServerManager(dccAcceptSocket, this)).start();
		}
		catch (Exception e)
		{
			String message = "!!! Failed to bind DCC socket on " +
							 DCCServerManager.LOCALHOST + ":" +
							 DCCServerManager.LOCALPORT;
			serverResponses.get(CLIENT).get(CLIENT).add(message);
			logger.severe(message);
		}

		for (String server : servers)
		{
			logger.info("Found configuration for server " + server);
			try
			{
				String[] serverOpts = getProperty("irc", server).split(",");
				IRCServer ircServer = new IRCServer(this, server, Short.parseShort(serverOpts[0]),
													userSession.getIrcName(), userSession.getIrcNick());
				ircServer.addObserver(this); //listen in for irc events
				userSession.setIrcCurrentServer(server);
				new Thread(ircServer).start();
				currentServer = ircServer;
				serverResponses.put(server, new Hashtable<String, LinkedList<String>>());
				serverResponses.get(server).put(server, new LinkedList <String>());
				ircServers.add(ircServer);
			}
			catch (NumberFormatException e) { e.printStackTrace(); }
		}
		userSession.setIrcClient(this);
	}

	public void connectAll() { for (IRCServer ircServer : ircServers) { ircServer.ircConnect(); } }

	public boolean join(String channel)
	{
		if (currentServer.isConnected() && currentServer.ircJoin(channel))
		{
			serverResponses.get(currentServer.getIrcServerAddress()).put(channel, new LinkedList<String>());
			return true;
		}
		else
		{
			String response = "!!! Failed to join " + channel + " on server " + currentServer.getIrcServerAddress();
			serverResponses.get(currentServer.getIrcServerAddress()).get(currentServer.getIrcServerAddress()).addLast(response);
			logger.severe(response);
			return false;
		}
	}

	public boolean part(String channel)
	{
		if (currentServer.isConnected() && currentServer.ircPart(channel))
		{
			serverResponses.get(currentServer.getIrcServerAddress()).remove(channel);
			return true;
		}
		else
		{
			String response = "!!! Failed to leave " + channel + " on server " + currentServer.getIrcServerAddress();
			serverResponses.get(currentServer.getIrcServerAddress()).get(currentServer.getIrcServerAddress()).addLast(response);
			logger.severe(response);
			return false;
		}
	}

	public void nick(String newNick) { if (currentServer.isConnected()) currentServer.ircNick(newNick); }

	public void voice(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircVoice(chan, nick); }

	public void devoice(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircDevoice(chan, nick); }

	public void op(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircOp(chan, nick); }

	public void deop(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircDeop(chan, nick); }

	public void ban(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircBan(chan, nick); }

	public void unban(String chan, String nick) { if (currentServer.isConnected()) currentServer.ircUnban(chan, nick); }

	public void kick(String chan, String nick, String reason) { if (currentServer.isConnected()) currentServer.ircKick(chan, nick, reason); }

	public void topic(String chan, String topic) { if (currentServer.isConnected()) currentServer.ircSetTopic(chan, topic); }

	public void dccChat(String nick) { if (currentServer.isConnected()) currentServer.ircDCCChat(nick); }

	public void identify() { identify(null); }

	public void identify(String password)
	{
		if (password == null) password = getProperty("irc", currentServer.getIrcServerAddress()).split(",")[3];
		if (!(currentServer.isConnected() && currentServer.ircIdentifyNick(password)))
		{
			String response = "!!! Failed to identify nick " + currentServer.getIrcCurrentNick() + " on server " + currentServer.getIrcServerAddress();
			serverResponses.get(currentServer.getIrcServerAddress()).get(currentServer.getIrcServerAddress()).addLast(response);
			logger.severe(response);
		}
	}

	public void identifyChannel()  { identifyChannel(null); }

	public void identifyChannel(String password) { identifyChannel(null, password); }

	public void identifyChannel(String channel, String password)
	{
		if (password == null) password = getProperty("irc", currentServer.getIrcServerAddress()).split(",")[3];
		if (channel == null) channel = currentChannel;
		if (!(currentServer.isConnected() && currentServer.ircIdentifyChannel(channel, password)))
		{
			String response = "Failed to identify nick " +
							  currentServer.getIrcCurrentNick() + " on channel " + channel +
							  currentServer.getIrcCurrentNick() + " on server " + currentServer.getIrcServerAddress();
			serverResponses.get(currentServer.getIrcServerAddress()).get(channel).addLast(response);
			logger.severe(response);
		}
	}

	public void privateMessage(String destination, String message)
	{
		if (destination == null) destination = currentChannel;
		currentServer.ircMsg(destination, message);
	}

	@Override
	public void update(Observable observable, Object o)
	{
		IRCServer source = (IRCServer)observable;
		ResponseMessage data = (ResponseMessage)o;

		if (serverResponses.get(source.getIrcServerAddress()) == null)
			serverResponses.put(source.getIrcServerAddress(), new Hashtable<String, LinkedList<String>>());

		if (serverResponses.get(source.getIrcServerAddress()).get(data.getSource()) == null)
			serverResponses.get(source.getIrcServerAddress()).put(data.getSource(), new LinkedList<String>());

		serverResponses.get(source.getIrcServerAddress()).get(data.getSource()).addLast(data.getMessage());
		switch (data.getIrcMsgType())
		{
			case CHANNEL:
			{
				logger.info(data.getIrcMsgType().name() + " >>> " + data.getSource() + " | " + data.getMessage());
				break;
			}
			case PRIVATE:
			{
				logger.info(data.getIrcMsgType().name() + " >>> " + data.getSource() + " | " + data.getMessage());
				if (data.getSource().equals(currentServer.getIrcCurrentNick())) handleBotCommands(source, data);
				break;
			}
			case SERVER:
			{
				logger.info(currentServer.getIrcServerAddress() + " | " + data.getMessage());
				break;
			}
			case DCC:
			{
				logger.info(data.getIrcMsgType().name() + " >>> " + data.getSource() + " | " + data.getMessage());
				break;
			}
		}
	}

	public StringBuffer getLatestUpdate(String server, String window)
	{
		System.out.println("getting latest update");
		StringBuffer result = new StringBuffer();
		if (window == null) window = server;

		if (serverResponses.get(CLIENT) != null && serverResponses.get(CLIENT).get(CLIENT) != null)
		{
			LinkedList<String> updates = serverResponses.get(CLIENT).get(CLIENT);
			while (!updates.isEmpty())
			{
				result.append(updates.getFirst() + "\r\n");
				updates.removeFirst();
			}
		}

		if (serverResponses.get(server) != null && serverResponses.get(server).get(window) != null)
		{
			LinkedList<String> updates = serverResponses.get(server).get(window);
			while (!updates.isEmpty())
			{
				result.append(updates.getFirst() + "\r\n");
				updates.removeFirst();
			}
		}
		return result;
	}

	private void handleBotCommands(IRCServer server, ResponseMessage responseMessage)
	{
		logger.info(">>> Handling response " + responseMessage.getRawResponse());

		//TODO check if nick is identified by nickserv

		String[] params = responseMessage.getMessage().split(" ");
		if      (responseMessage.getMessage().startsWith("!part ")) server.ircPart(params[1]);
		else if (responseMessage.getMessage().startsWith("!join ")) server.ircJoin(params[1]);
		else if (responseMessage.getMessage().startsWith("!nick ")) server.ircNick(params[1]);
		else if (responseMessage.getMessage().startsWith("!op ")) server.ircOp(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!deop ")) server.ircDeop(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!voice ")) server.ircVoice(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!devoice ")) server.ircDevoice(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!ban ")) server.ircBan(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!unban ")) server.ircUnban(params[1], params[2]);
		else if (responseMessage.getMessage().startsWith("!kick ")) server.ircKick(params[1], params[2], params[3]);
		else if (responseMessage.getMessage().startsWith("!topic ")) server.ircSetTopic(params[1], params[2]);
	}

	public void closeSession()
	{
		currentServer.closeSession();
		try { this.finalize(); }
		catch (Throwable e) { e.printStackTrace(); }
	}
}
