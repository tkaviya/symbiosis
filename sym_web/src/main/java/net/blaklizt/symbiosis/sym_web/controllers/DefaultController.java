package net.blaklizt.symbiosis.sym_web.controllers;

import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator;
import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisUserDetails;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_persistence.dao.impl.SymbiosisUserDaoImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Controller
public class DefaultController
{
	@Autowired SymbiosisAuthenticator symbiosisAuthenticator;

	@Autowired
	SymbiosisUserDaoImpl userDao;

	private Logger logger = Configuration.getNewLogger(DefaultController.class.getSimpleName());
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root()
    {
        ModelMap model = new ModelMap();
        return new ModelAndView("index.jsp", model);
    }

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(HttpServletRequest request, HttpServletResponse response)
	{
		String jsonResponse;
		try
		{
			SymbiosisUserDetails userDetails = symbiosisAuthenticator.loadUserByUsername(request.getParameter("username"));
			
//			Date lastAccessDate = userDetails.getSymbiosisUser().getLastLoginDate();
			
			ResponseCode authResponse = symbiosisAuthenticator.authenticateUser(userDetails);
			
			
			if (authResponse == ResponseCode.SUCCESS)
			{
				logger.info("Authentication successful.");
				JSONObject responseJSON = new JSONObject(ResponseCode.SUCCESS.toJSONResponse());
				responseJSON.put("auth_token", userDetails.getSymbiosisUser().getAuthToken());
//				responseJSON.put("last_access_date", sdf.format(lastAccessDate));
				jsonResponse = responseJSON.toString();
				
			}
			else
			{
				jsonResponse = ResponseCode.AUTHENTICATION_FAILED.toJSONResponse();
			}
		}
		catch (Exception ex)
		{
			logger.severe("Failed to authenticate:\n" + ex.getMessage());
			jsonResponse = ResponseCode.GENERAL_ERROR.toJSONResponse();
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request)
	{
		logger.info("Got registration request.");

		SymbiosisUserDetails userDetails = symbiosisAuthenticator.loadUserByUsername(request.getParameter("username"));

		ResponseCode responseCode;
		String jsonResponse;

		if (userDetails != null) jsonResponse = ResponseCode.PREVIOUS_REGISTRATION_FOUND.toString();
		else
		{
			try
			{
				logger.info("Got registration request.");
				jsonResponse = ResponseCode.SUCCESS.toJSONResponse();
				//response.setStatus(HttpStatus.OK.value());
			}
			catch (Exception ex)
			{
				logger.severe("Failed to authenticate: " + ex.getMessage());
				jsonResponse = ResponseCode.GENERAL_ERROR.toJSONResponse();
			}
			return jsonResponse;


		}

		logger.info("Returning response:\n" + jsonResponse);
		return jsonResponse;
	}
}
