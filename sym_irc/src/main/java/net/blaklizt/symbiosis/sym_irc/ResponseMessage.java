package net.blaklizt.symbiosis.sym_irc;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 12/1/13
 * Time: 12:55 PM
 */

public class ResponseMessage
{
	public enum IRC_MSG_TYPE { SERVER, CHANNEL, PRIVATE, DCC; }

	private String rawResponse;
	private IRC_MSG_TYPE ircMsgType;
	private String source;
	private String message;

	public ResponseMessage(String rawResponse, IRC_MSG_TYPE ircMsgType, String source, String message)
	{
		this.rawResponse = rawResponse;
		this.ircMsgType = ircMsgType;
		this.source = source;
		this.message = message;
	}

	public String getRawResponse() { return rawResponse; }

	public String getMessage() { return message; }

	public IRC_MSG_TYPE getIrcMsgType() { return ircMsgType; }

	public String getSource() { return source; }
}