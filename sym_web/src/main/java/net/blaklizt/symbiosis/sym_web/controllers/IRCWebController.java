package net.blaklizt.symbiosis.sym_web.controllers;

import net.blaklizt.symbiosis.sym_common.utilities.Format;
import net.blaklizt.symbiosis.sym_irc.session.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/07/06
 * Time: 1:02 PM
 */

@Controller
public class IRCWebController
{
	private static final Logger log4j = Logger.getLogger(IRCWebController.class.getSimpleName());

    @RequestMapping(value="/process", method = { RequestMethod.GET , RequestMethod.POST } )
    public @ResponseBody String process(HttpServletRequest request, @ModelAttribute(value="menu_response") String response)
    {
		UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
		log4j.info("Got user input: " + response);

		if (response.startsWith("/"))
		{
			String[] data = response.split(" ");

			try
			{
				if (data[0].equalsIgnoreCase("/nick")) userSession.getIrcClient().nick(data[1]);
				else if (data[0].equalsIgnoreCase("/msg")) userSession.getIrcClient().privateMessage(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/join"))
				{
					if (userSession.getIrcClient().join(data[1])) userSession.setIrcCurrentChannel(data[0]);
				}
				else if (data[0].equalsIgnoreCase("/part"))
				{
					if (userSession.getIrcClient().part(data[1])) userSession.setIrcCurrentChannel(null);
				}
				else if (data[0].equalsIgnoreCase("/op")) userSession.getIrcClient().op(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/deop")) userSession.getIrcClient().deop(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/voice")) userSession.getIrcClient().voice(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/devoice")) userSession.getIrcClient().devoice(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/ban")) userSession.getIrcClient().ban(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/unban")) userSession.getIrcClient().unban(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/kick")) userSession.getIrcClient().kick(data[1], data[2], data[3]);
				else if (data[0].equalsIgnoreCase("/topic")) userSession.getIrcClient().topic(data[1], data[2]);
				else if (data[0].equalsIgnoreCase("/dcc"))
				{
					if (data.length >= 3)
					{
						System.out.println("doing dcc");
						if (data[1].equalsIgnoreCase("chat"))
						{
							System.out.println("doing dcc chat");
							userSession.getIrcClient().dccChat(data[2]);
						}
					}
				}
				else if (data[0].equalsIgnoreCase("/identify"))
				{
					switch (data.length) {
						case 1: userSession.getIrcClient().identify();				break;
						case 2: userSession.getIrcClient().identify(data[1]);		break;
					}
				}
				else if (data[0].equalsIgnoreCase("/identifyChan"))
				{
					switch (data.length) {
						case 1: userSession.getIrcClient().identifyChannel();					break;
						case 2: userSession.getIrcClient().identifyChannel(data[1]);			break;
						case 3: userSession.getIrcClient().identifyChannel(data[1], data[2]);	break;
					}
				}
			}
			catch (IndexOutOfBoundsException e) { Format.formatBlink("Insufficient parameters for command " + data[0]); }
			return response.replaceFirst("/", "*** ");
		}
		else return response;
    }

	@RequestMapping(value= "/getEvents", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/event-stream")
	public @ResponseBody String getEvents(HttpServletRequest request)
//		   @ModelAttribute(value="server") String server,
//		   @ModelAttribute(value="window") String window)
	{
		try
		{
			UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
			if (userSession != null)
			{
				StringBuffer updates = userSession.getIrcClient().getLatestUpdate(
						userSession.getIrcCurrentServer(),
						userSession.getIrcCurrentChannel());
				if (updates.length() > 0) return ("data:" + updates.toString().replaceAll("\r\n", "<br/>") + "\r\n\r\n");
			}
			return "retry: 5000\r\ndata:\r\n\r\n";
		}
		catch (Exception e) { e.printStackTrace(); return "retry: 5000\ndata:\r\n\r\n"; }
	}
}