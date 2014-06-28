package net.blaklizt.symbiosis.sym_irc;

import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_irc.dcc.DCCClientManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 11/26/13
 * Time: 10:37 AM
 */
public class IRCServer extends Observable implements Runnable
{
 	protected static final Integer IRC_RPL_WELCOME = 001;	//Welcome to the Internet Relay Network <nick>!<user>@<host>
	protected static final Integer IRC_RPL_YOURHOST = 002;	//Your host is <servername>, running version <ver>
	protected static final Integer IRC_RPL_CREATED = 003;	//This server was created <date>
	protected static final Integer IRC_RPL_MYINFO = 004;	//<servername> <version> <available user modes>
	protected static final Integer IRC_RPL_BOUNCE = 005;	//Try server <server name>, port <port number>
	protected static final Integer IRC_RPL_USERHOST = 302;	//:*1<reply>
	protected static final Integer IRC_RPL_ISON = 303;		//:*1<nick>
	protected static final Integer IRC_RPL_AWAY = 301;		//<nick> :<away message>
	protected static final Integer IRC_RPL_UNAWAY = 305;	//:You are no longer marked as being away
	protected static final Integer IRC_RPL_NOWAWAY = 306; 			//:You have been marked as being away
	protected static final Integer IRC_RPL_WHOISUSER = 311; 		//<nick> <user> <host> * :<real name>
	protected static final Integer IRC_RPL_WHOISSERVER = 312;		//<nick> <server> :<server info>
	protected static final Integer IRC_RPL_WHOISOPERATOR = 313;		//<nick> :is an IRC operator
	protected static final Integer IRC_RPL_WHOISIDLE = 317;			//<nick> <integer> :seconds idle
	protected static final Integer IRC_RPL_ENDOFWHOIS = 318;		//<nick> :End of WHOIS list
	protected static final Integer IRC_RPL_WHOISCHANNELS = 319;		//"<nick> :*( ( "@" / "+" ) <channel> " " )"
	protected static final Integer IRC_RPL_WHOWASUSER = 314;		//<nick> <user> <host> * :<real name>
	protected static final Integer IRC_RPL_ENDOFWHOWAS = 369;		//<nick> :End of WHOWAS
	protected static final Integer IRC_RPL_LIST = 322;				//<channel> <# visible> :<topic>
	protected static final Integer IRC_RPL_LISTEND = 323;			//:End of LIST
	protected static final Integer IRC_RPL_UNIQOPIS = 325;			//<channel> <nickname>
	protected static final Integer IRC_RPL_CHANNELMODEIS = 324;		//<channel> <mode> <mode params>
	protected static final Integer IRC_RPL_NOTOPIC = 331;			//<channel> :No topic is set
	protected static final Integer IRC_RPL_TOPIC = 332;				//<channel> :<topic>
	protected static final Integer IRC_RPL_INVITING = 341;			//<channel> <nick>
	protected static final Integer IRC_RPL_SUMMONING = 342;			//<user> :Summoning user to IRC
	protected static final Integer IRC_RPL_INVITELIST = 346;		//<channel> <invitemask>
	protected static final Integer IRC_RPL_ENDOFINVITELIST = 347;	//<channel> :End of channel invite list
	protected static final Integer IRC_RPL_EXCEPTLIST = 348;		//<channel> <exceptionmask>
	protected static final Integer IRC_RPL_ENDOFEXCEPTLIST = 349;	//<channel> :End of channel exception list
	protected static final Integer IRC_RPL_VERSION = 351;			//<version>.<debuglevel> <server> :<comments>
	protected static final Integer IRC_RPL_WHOREPLY = 352;			//<channel> <user> <host> <server> <nick> ( "H
	protected static final Integer IRC_RPL_ENDOFWHO = 315;			//<name> :End of WHO list
	protected static final Integer IRC_RPL_NAMREPLY = 353;			//( "=
	protected static final Integer IRC_RPL_ENDOFNAMES = 366;		//<channel> :End of NAMES list
	protected static final Integer IRC_RPL_LINKS = 364;				//<mask> <server> :<hopcount> <server info>
	protected static final Integer IRC_RPL_ENDOFLINKS = 365;		//<mask> :End of LINKS list
	protected static final Integer IRC_RPL_BANLIST = 367;			//<channel> <banmask>
	protected static final Integer IRC_RPL_ENDOFBANLIST = 368;		//<channel> :End of channel ban list
	protected static final Integer IRC_RPL_INFO = 371;				//:<string>
	protected static final Integer IRC_RPL_ENDOFINFO = 374;			//:End of INFO list
	protected static final Integer IRC_RPL_MOTDSTART = 375;			//:- <server> Message of the day -
	protected static final Integer IRC_RPL_MOTD = 372;				//:- <text>
	protected static final Integer IRC_RPL_ENDOFMOTD = 376;			//:End of MOTD command
	protected static final Integer IRC_RPL_YOUREOPER = 381;			//:You are now an IRC operator
	protected static final Integer IRC_RPL_REHASHING = 382;			//:Rehashing
	protected static final Integer IRC_RPL_YOURESERVICE = 383;		//You are service <servicename>
	protected static final Integer IRC_RPL_TIME = 391;				//<server> :<string showing server's local time>
	protected static final Integer IRC_RPL_USERSSTART = 392;		//:UserID   Terminal  Host
	protected static final Integer IRC_RPL_USERS = 393;				//:<username> <ttyline> <hostname>
	protected static final Integer IRC_RPL_ENDOFUSERS = 394;		//:End of users
	protected static final Integer IRC_RPL_NOUSERS = 395;			//:Nobody logged in
	protected static final Integer IRC_RPL_TRACELINK = 200;			//Link <version & debug level> <destination>
																	//<next server> V<protocol version>
																	//<link uptime in seconds> <backstream sendq>
																	//<upstream sendq>
	protected static final Integer IRC_RPL_TRACECONNECTING = 201;	//Try. <class> <server>
	protected static final Integer IRC_RPL_TRACEHANDSHAKE = 202;	//H.S. <class> <server>
	protected static final Integer IRC_RPL_TRACEUNKNOWN = 203;		//???? <class> [<client IP address in dot form>]
	protected static final Integer IRC_RPL_TRACEOPERATOR = 204;		//Oper <class> <nick>
	protected static final Integer IRC_RPL_TRACEUSER = 205;			//User <class> <nick>
	protected static final Integer IRC_RPL_TRACESERVER = 206;		//Serv <class> <int>S <int>C <server>
	protected static final Integer IRC_RPL_TRACESERVICE = 207;		//Service <class> <name> <type> <active type>
	protected static final Integer IRC_RPL_TRACENEWTYPE = 208;		//<newtype> 0 <client name>
	protected static final Integer IRC_RPL_TRACECLASS = 209;		//Class <class> <count>
	protected static final Integer IRC_RPL_TRACELOG = 261;			//File <logfile> <debug level>
	protected static final Integer IRC_RPL_TRACEEND = 262;			//<server name> <version & debug level> :End of TRACE
	protected static final Integer IRC_RPL_STATSLINKINFO = 211;		//<linkname> <sendq> <sent messages>
																	//<sent Kbytes> <received messages>
																	//<received Kbytes> <time open>
	protected static final Integer IRC_RPL_STATSCOMMANDS = 212;		//<command> <count> <byte count> <remote count>
	protected static final Integer IRC_RPL_ENDOFSTATS = 219;		//<stats letter> :End of STATS report
	protected static final Integer IRC_RPL_STATSUPTIME = 242;		//:Server Up %d days %d:%02d:%02d
	protected static final Integer IRC_RPL_STATSOLINE = 243;		//O <hostmask> * <name>
	protected static final Integer IRC_RPL_UMODEIS = 221;			//<user mode string>
	protected static final Integer IRC_RPL_SERVLIST = 234;			//<name> <server> <mask> <type> <hopcount> <info>
	protected static final Integer IRC_RPL_SERVLISTEND = 235;		//<mask> <type> :End of service listing
	protected static final Integer IRC_RPL_LUSERCLIENT = 251;		//:There are <integer> users and <integer>
	protected static final Integer IRC_RPL_LUSEROP = 252;			//<integer> :operator(s) online
	protected static final Integer IRC_RPL_LUSERUNKNOWN = 253;		//<integer> :unknown connection(s)
	protected static final Integer IRC_RPL_LUSERCHANNELS = 254;		//<integer> :channels formed
	protected static final Integer IRC_RPL_LUSERME = 255;			//:I have <integer> clients and <integer> servers
	protected static final Integer IRC_RPL_ADMINME = 256;			//<server> :Administrative info
	protected static final Integer IRC_RPL_ADMINLOC1 = 257;			//:<admin info>
	protected static final Integer IRC_RPL_ADMINLOC2 = 258;			//:<admin info>
	protected static final Integer IRC_RPL_ADMINEMAIL = 259;		//:<admin info>
	protected static final Integer IRC_RPL_TRYAGAIN = 263;			//<command> :Please wait a while and try again.
	protected static final Integer IRC_ERR_NOSUCHNICK = 401;		//<nickname> :No such nick/channel
	protected static final Integer IRC_ERR_NOSUCHSERVER = 402;		//<server name> :No such server
	protected static final Integer IRC_ERR_NOSUCHCHANNEL = 403;		//<channel name> :No such channel
	protected static final Integer IRC_ERR_CANNOTSENDTOCHAN = 404;	//<channel name> :Cannot send to channel
	protected static final Integer IRC_ERR_TOOMANYCHANNELS = 405;	//<channel name> :You have joined too many channels
	protected static final Integer IRC_ERR_WASNOSUCHNICK = 406;		//<nickname> :There was no such nickname
	protected static final Integer IRC_ERR_TOOMANYTARGETS = 407;	//<target> :<error code> recipients. <abort message>
	protected static final Integer IRC_ERR_NOSUCHSERVICE = 408;		//<service name> :No such service
	protected static final Integer IRC_ERR_NOORIGIN = 409;			//:No origin specified
	protected static final Integer IRC_ERR_NORECIPIENT = 411;		//:No recipient given (<command>)
	protected static final Integer IRC_ERR_NOTEXTTOSEND = 412;		//:No text to send
	protected static final Integer IRC_ERR_NOTOPLEVEL = 413;		//<mask> :No toplevel domain specified
	protected static final Integer IRC_ERR_WILDTOPLEVEL = 414;		//<mask> :Wildcard in toplevel domain
	protected static final Integer IRC_ERR_BADMASK = 415;			//<mask> :Bad Server/host mask
	protected static final Integer IRC_ERR_UNKNOWNCOMMAND = 421;	//<command> :Unknown command
	protected static final Integer IRC_ERR_NOMOTD = 422;			//:MOTD File is missing
	protected static final Integer IRC_ERR_NOADMININFO = 423;		//<server> :No administrative info available
	protected static final Integer IRC_ERR_FILEERROR = 424;			//:File error doing <file op> on <file>
	protected static final Integer IRC_ERR_NONICKNAMEGIVEN = 431;	//:No nickname given
	protected static final Integer IRC_ERR_ERRONEUSNICKNAME = 432;	//<nick> :Erroneous nickname
	protected static final Integer IRC_ERR_NICKNAMEINUSE = 433;		//<nick> :Nickname is already in use
	protected static final Integer IRC_ERR_NICKCOLLISION = 436;		//<nick> :Nickname collision KILL from <user>@<host>
	protected static final Integer IRC_ERR_UNAVAILRESOURCE = 437;	//<nick/channel> :Nick/channel is temporarily unavailable
	protected static final Integer IRC_ERR_USERNOTINCHANNEL = 441;	//<nick> <channel> :They aren't on that channel
	protected static final Integer IRC_ERR_NOTONCHANNEL = 442;		//<channel> :You're not on that channel
	protected static final Integer IRC_ERR_USERONCHANNEL = 443;		//<user> <channel> :is already on channel
	protected static final Integer IRC_ERR_NOLOGIN = 444;			//<user> :User not logged in
	protected static final Integer IRC_ERR_SUMMONDISABLED = 445;	//:SUMMON has been disabled
	protected static final Integer IRC_ERR_USERSDISABLED = 446;		//:USERS has been disabled
	protected static final Integer IRC_ERR_NOTREGISTERED = 451;		//:You have not registered
	protected static final Integer IRC_ERR_NEEDMOREPARAMS = 461;	//<command> :Not enough parameters
	protected static final Integer IRC_ERR_ALREADYREGISTRED = 462;	//:Unauthorized command (already registered)
	protected static final Integer IRC_ERR_NOPERMFORHOST = 463;		//:Your host isn't among the privileged
	protected static final Integer IRC_ERR_PASSWDMISMATCH = 464;	//:Password incorrect
	protected static final Integer IRC_ERR_YOUREBANNEDCREEP = 465;	//:You are banned from this server
	protected static final Integer IRC_ERR_YOUWILLBEBANNED = 466;	//:You will be banned from this server
	protected static final Integer IRC_ERR_KEYSET = 467;			//<channel> :Channel key already set
	protected static final Integer IRC_ERR_CHANNELISFULL = 471;		//<channel> :Cannot join channel (+l)
	protected static final Integer IRC_ERR_UNKNOWNMODE = 472;		//<char> :is unknown mode char to me for <channel>
	protected static final Integer IRC_ERR_INVITEONLYCHAN = 473;	//<channel> :Cannot join channel (+i)
	protected static final Integer IRC_ERR_BANNEDFROMCHAN = 474;	//<channel> :Cannot join channel (+b)
	protected static final Integer IRC_ERR_BADCHANNELKEY = 475;		//<channel> :Cannot join channel (+k)
	protected static final Integer IRC_ERR_BADCHANMASK = 476;		//<channel> :Bad Channel Mask
	protected static final Integer IRC_ERR_NOCHANMODES = 477;		//<channel> :Channel doesn't support modes
	protected static final Integer IRC_ERR_BANLISTFULL = 478;		//<channel> <char> :Channel list is full
	protected static final Integer IRC_ERR_NOPRIVILEGES = 481;		//:Permission Denied- You're not an IRC operator
	protected static final Integer IRC_ERR_CHANOPRIVSNEEDED = 482;	//<channel> :You're not channel operator
	protected static final Integer IRC_ERR_CANTKILLSERVER = 483;	//:You can't kill a server!
	protected static final Integer IRC_ERR_RESTRICTED = 484;		//:Your connection is restricted!
	protected static final Integer IRC_ERR_UNIQOPPRIVSNEEDED = 485;	//:You're not the original channel operator
	protected static final Integer IRC_ERR_NOOPERHOST = 491;		//:No O-lines for your host
	protected static final Integer IRC_ERR_UMODEUNKNOWNFLAG = 501;	//:Unknown MODE flag
	protected static final Integer IRC_ERR_USERSDONTMATCH = 502;	//:Cannot change mode for other users


	protected static final Logger log4j = Logger.getLogger(IRCServer.class.getSimpleName());
	protected static final String VERSION_RESPONSE = CommonUtilities.getConfiguration("irc", "VERSION_RESPONSE");
	protected static final String PING_RESPONSE = CommonUtilities.getConfiguration("irc", "PONG_RESPONSE");
	protected IRCClient ircClient;
	protected SocketChannel socketChannel;
	protected String localHostAddress;
	protected String ircServerAddress;
	protected short ircServerPort;
	protected String ircCurrentName;
	protected String ircCurrentNick;
	protected LinkedList<String> ircChannels = new LinkedList<>();
	protected Hashtable<String,LinkedList<String>> userList = new Hashtable<>();

	protected String ircWhoIsResponseNick;
	protected String ircWhoIsResponseName;
	protected String ircWhoIsResponseHost;


	public void closeSession()
	{
		log4j.info("Closing connection to " + ircServerAddress + ":" + ircServerPort);
		try { if (socketChannel != null) socketChannel.close(); this.finalize(); }
		catch (Throwable e) { e.printStackTrace(); }
	}

	public IRCServer(IRCClient ircClient, String ircServerAddress, short ircServerPort,
					 String ircCurrentName, String ircCurrentNick)
	{
		this.ircClient = ircClient;
		this.ircServerAddress = ircServerAddress.trim();
		this.ircServerPort = ircServerPort;
		this.ircCurrentName = ircCurrentName.trim();
		this.ircCurrentNick = ircCurrentNick.trim();
	}

	public boolean isConnected() { return socketChannel != null && socketChannel.isConnected(); }

	public String getIrcServerAddress() { return ircServerAddress; }

	public String getIrcCurrentNick() { return ircCurrentNick; }

	public void ircConnect()
	{
		String message = ">>> Connecting to server " + ircServerAddress + ":" + ircServerPort;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER,ircServerAddress,message));
		log4j.info(message);

		Thread connectThread = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					InetSocketAddress socketAddress = new InetSocketAddress(ircServerAddress, ircServerPort);
					socketChannel = SocketChannel.open(socketAddress);
					socketChannel.configureBlocking(false);
					if (socketChannel.isConnected())
					{
						//connection succeeded, get local hostname/IP
						String message = ">>> Connected to server, sending nick, name & host...";
						notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
						log4j.info(message);

						InetSocketAddress localAddress = (InetSocketAddress)socketChannel.getLocalAddress();
						localHostAddress = localAddress.getAddress().getHostAddress();
						ircNick(ircCurrentNick);
						ircSendRaw("USER " + ircCurrentNick + " " +
								   			 localHostAddress + " " +
											 ircServerAddress + " :" +
											 ircCurrentName + "\r\n");
					}

				}
				catch (UnresolvedAddressException u)
				{
					String message = "!!! Failed to resolve host " + ircServerAddress + ". " + u.getMessage();
					notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
					log4j.info(message);
				}
				catch (IOException e)
				{
					String message = "!!! Failed to connect to " + ircServerAddress + ":" + ircServerPort;
					notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
					log4j.info(message);
				}
			}
		};
		connectThread.start();
	}

	public boolean ircSendRaw(String command)
	{
		log4j.info("*** [" + command.replaceAll("\r\n","") + "]");
		ByteBuffer buffer = ByteBuffer.wrap(command.getBytes());
		try { return socketChannel.write(buffer) > 0; } catch (Exception e) { e.printStackTrace(); return false; }
	}

	public boolean ircJoin(String channel)
	{
		String message = ">>> Joining channel " + channel;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
		log4j.info(message);

		//check if channel is valid
		if (channel == null || channel.trim().length() == 0) return false;
		channel = channel.trim();
		if (!channel.startsWith("#")) return false;
		if (channel.contains(" ")) return false;

		if (ircSendRaw("JOIN " + channel + "\r\n"))
		{
			ircChannels.add(channel);
			ircGetUserList(channel);
			return true;
		}
		else return false;
	}

	public boolean ircPart(String channel)
	{

		String message = ">>> Leaving channel " + channel;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
		log4j.info(message);

		//check if channel is valid
		if (channel == null || channel.trim().length() == 0) return false;
		channel = channel.trim();
		if (!channel.startsWith("#")) return false;
		if (channel.contains(" ")) return false;

		if (ircSendRaw("PART " + channel + "\r\n"))
		{
			ircChannels.remove(channel);
			userList.remove(channel);
			return true;
		}
		else return false;
	}

	void ircOnJoin(String nick, String channel)
	{
		String message = "* " + nick + " has joined channel " + channel;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.CHANNEL, channel, message));
		userList.get(channel.trim()).add(nick.trim());
	}

	void ircOnPart(String nick, String channel)
	{
		String message = "* " + nick + " has left channel " + channel;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.CHANNEL, channel, message));
		userList.get(channel.trim()).remove(nick.trim());
	}

	boolean ircNick(String newNick)
	{
		String message = ">>> Setting nick to " + newNick;
		notifyObservers(new ResponseMessage(message, ResponseMessage.IRC_MSG_TYPE.SERVER, ircServerAddress, message));
		log4j.info(message);

		//check if nick is valid
		if (newNick == null || newNick.trim().length() == 0) return false;
		newNick = newNick.trim();
		if (newNick.contains(" ")) return false;

		if (ircSendRaw("NICK " + newNick + "\r\n"))
		{
			for (String key : userList.keySet())
			{
				userList.get(key).remove(ircCurrentNick);
				userList.get(key).addFirst(newNick);
			}
			ircCurrentNick = newNick;
			return true;
		}
		else return false;
	}

	boolean ircMsg(String destination, String message)		{ return ircSendRaw("PRIVMSG " + destination.trim() + " :" + message + "\r\n"); }
	boolean ircIdentifyNick(String pass)					{ return ircSendRaw("PRIVMSG nickserv :identify " + pass.trim() + "\r\n"); }
	boolean ircIdentifyChannel(String chan, String pass)	{ return ircSendRaw("PRIVMSG chanserv :identify " + chan.trim() + " " + pass.trim() + "\r\n"); }
	boolean ircOp(String chan, String nick)			{ return ircSendRaw("MODE " + chan.trim() + " +o " + nick.trim() + "\r\n"); }
	boolean ircDeop(String chan, String nick)		{ return ircSendRaw("MODE " + chan.trim() + " -o " + nick.trim() + "\r\n"); }
	boolean ircVoice(String chan, String nick)		{ return ircSendRaw("MODE " + chan.trim() + " +v " + nick.trim() + "\r\n"); }
	boolean ircDevoice(String chan, String nick)	{ return ircSendRaw("MODE " + chan.trim() + " -v " + nick.trim() + "\r\n"); }
	boolean ircBan(String chan, String nick)		{ return ircSendRaw("MODE " + chan.trim() + " +b " + nick.trim() + "\r\n"); }
	boolean ircUnban(String chan, String nick)		{ return ircSendRaw("MODE " + chan.trim() + " -b " + nick.trim() + "\r\n"); }
	boolean ircKick(String chan, String nick, String reason){ return ircSendRaw("KICK " + chan.trim() + " " + nick.trim() + " :" + reason + "\r\n"); }
	boolean ircSetTopic(String chan, String topic)	{ return ircSendRaw("TOPIC " + chan.trim() + " :" + topic + "\r\n"); }
	boolean ircNotice(String nick, String message)	{ return ircSendRaw("NOTICE " + nick.trim() + " :" + message + "\r\n"); }
	boolean ircGetUserList(String chan)				{ return ircSendRaw("NAMES " + chan.trim() + "\r\n"); }
	boolean ircDCCChat(String nick)
	{
		return ircSendRaw("PRIVMSG " + nick +" DCC Chat chat " + localHostAddress + " " +
			CommonUtilities.getConfiguration("irc", "dccLocalPort") + "\r\n");
	}

	void ircPong()									{ ircSendRaw("PONG " + ircServerAddress + "\r\n"); }
	void ircSendVersionResponse(String nick)		{ ircNotice(nick.trim(),'\1' + VERSION_RESPONSE + '\1'); }
	void ircSendPingResponse(String nick)			{ ircNotice(nick.trim(), '\1' + PING_RESPONSE + '\1'); }

	@Override
	public void notifyObservers(Object arg) { setChanged(); super.notifyObservers(arg); }

	@Override
	public void run()
	{
		ByteBuffer buffer = ByteBuffer.allocate(10240);
		final int wait = 1000;

		while (true)
		{
			while (socketChannel == null || !socketChannel.isConnected())
			{
				try { Thread.sleep(wait); } catch (Exception e) { return; }
			}

			while (socketChannel.isConnected())
			{
				try
				{
					buffer.clear();
					int count;
					if ((count = socketChannel.read(buffer)) > 0)
					{
						//receive
						String receivedStr = "", sentIdentity, sentNick, sentArgs;
						for (int c = 0; c < count; c++) receivedStr += (char)buffer.get(c);

						log4j.info(receivedStr);

						//test if numeric response code
						if (receivedStr.matches("^[0-9]{3}+\\."))
						{
							//numeric response
							String[] details = receivedStr.split(" ");
							if (Integer.parseInt(receivedStr.substring(0,3)) == IRC_RPL_WELCOME)
							{
								localHostAddress = details[details.length - 1].split("@")[1];
							}
							else if (Integer.parseInt(receivedStr.substring(0,3)) == IRC_RPL_YOURHOST)
							{
								localHostAddress = receivedStr.split(" ")[3];
							}
							else if (Integer.parseInt(receivedStr.substring(0,3)) == IRC_RPL_USERHOST)
							{
								localHostAddress = receivedStr.substring(3);
							}
							else if (Integer.parseInt(receivedStr.substring(0,3)) == IRC_RPL_WHOISUSER)
							{
								ircWhoIsResponseNick = details[details.length - 1].split(" ")[0];
								ircWhoIsResponseName = details[details.length - 1].split(" ")[4].substring(1);
								ircWhoIsResponseHost = details[details.length - 1].split(" ")[2];
							}


//							protected static final Integer IRC_RPL_WHOISUSER = 311; 		//<nick> <user> <host> * :<real name>
//							protected static final Integer IRC_RPL_NOTOPIC = 331;			//<channel> :No topic is set
//							protected static final Integer IRC_RPL_TOPIC = 332;				//<channel> :<topic>
//							protected static final Integer IRC_RPL_USERS = 393;				//:<username> <ttyline> <hostname>
//							protected static final Integer IRC_RPL_ENDOFUSERS = 394;		//:End of users
//							protected static final Integer IRC_ERR_NOSUCHNICK = 401;		//<nickname> :No such nick/channel
//							protected static final Integer IRC_ERR_NOSUCHSERVER = 402;		//<server name> :No such server
//							protected static final Integer IRC_ERR_NOSUCHCHANNEL = 403;		//<channel name> :No such channel
//							protected static final Integer IRC_ERR_CANNOTSENDTOCHAN = 404;	//<channel name> :Cannot send to channel
//							protected static final Integer IRC_ERR_TOOMANYCHANNELS = 405;	//<channel name> :You have joined too many channels
//							protected static final Integer IRC_ERR_NOTONCHANNEL = 442;		//<channel> :You're not on that channel
//							protected static final Integer IRC_ERR_NOLOGIN = 444;			//<user> :User not logged in
//							protected static final Integer IRC_ERR_NOTREGISTERED = 451;		//:You have not registered
//							protected static final Integer IRC_ERR_PASSWDMISMATCH = 464;	//:Password incorrect


						}
						else
						{
							if (receivedStr.startsWith("PING ")) ircPong();
							else if (receivedStr.startsWith("JOIN "))
							{
								sentIdentity = receivedStr.split(" ")[0].substring(1);
								sentNick = sentIdentity.split("!")[0];
								sentArgs = receivedStr.split(" ")[2];
								ircOnJoin(sentNick, sentArgs);
							}
							else if (receivedStr.startsWith("PART "))
							{
								sentIdentity = receivedStr.split(" ")[0].substring(1);
								sentNick = sentIdentity.split("!")[0];
								sentArgs = receivedStr.split(" ")[2];
								ircOnPart(sentNick, sentArgs);
							}
							else if (receivedStr.startsWith("PRIVMSG "))
							{
								String[] details = receivedStr.split(" ");
								if (details[1].equalsIgnoreCase('\1' + "VERSION" + '\1'))
								{
									sentIdentity = details[0].substring(1);
									sentNick = sentIdentity.split("!")[0];
									ircSendVersionResponse(sentNick);
								}
								else if (details[1].equalsIgnoreCase('\1' + "PING" + '\1'))
								{
									sentIdentity = details[0].substring(1);
									sentNick = sentIdentity.split("!")[0];
									ircSendPingResponse(sentNick);
								}
								else if (details[1].equalsIgnoreCase('\1' + "DCC" + '\1'))
								{
									if (details[2].equalsIgnoreCase("CHAT "))
									{
										if (details[3].equalsIgnoreCase("chat "))
										{
											try
											{
												sentIdentity = details[0].substring(1);
												sentNick = sentIdentity.split("!")[0];
												String remoteAddress = details[4], remotePort = details[5];
												DCCClientManager dccClientManager = new DCCClientManager(
														sentNick, remoteAddress, Short.parseShort(remotePort));
												dccClientManager.addObserver(ircClient);
												new Thread(dccClientManager).start();
											}
											catch (NumberFormatException ex) { ex.printStackTrace(); }

										}
									}
								}
								else
								{
									sentIdentity = details[0].substring(1);
									sentNick = sentIdentity.split("!")[0];
									notifyObservers(new ResponseMessage(
											receivedStr, ResponseMessage.IRC_MSG_TYPE.PRIVATE,
											sentNick, details[2]));
								}
							}
						}
					}
					else socketChannel.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					if (!socketChannel.isConnected()) ircConnect();
				}
			}
		}
	}
}
