package net.blaklizt.symbiosis.sym_irc.session;

import net.blaklizt.symbiosis.sym_irc.IRCClient;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/07/08
 * Time: 11:32 PM
 */
public class UserSession
{
	public enum SessionType
	{
		PLAIN_TEXT,
		HTML
	}

    protected String ircName;
	protected String ircNick;
	protected String ircPassword;
	protected String ircCurrentServer;
	protected String ircCurrentChannel;
	protected IRCClient ircClient;

	private SessionType sessionType = SessionType.PLAIN_TEXT;

	public UserSession(SessionType sessionType, String ircName, String ircNick, String ircPassword)
	{
		this.sessionType = sessionType;
		this.ircName = ircName;
		this.ircNick = ircNick;
		this.ircPassword = ircPassword;
	}

	public String getIrcPassword() { return ircPassword; }

	public String getIrcName() { return ircName; }

	public String getIrcNick() { return ircNick; }

	public String getIrcCurrentServer() { return ircCurrentServer; }

	public String getIrcCurrentChannel() { return ircCurrentChannel; }

	public void setIrcCurrentServer(String ircCurrentServer) { this.ircCurrentServer = ircCurrentServer; }

	public void setIrcCurrentChannel(String ircCurrentChannel) { this.ircCurrentChannel = ircCurrentChannel; }

	public void setIrcClient(IRCClient ircClient) { this.ircClient = ircClient; }

	public IRCClient getIrcClient() { return ircClient; }

	public SessionType getSessionType() { return sessionType; }

}
