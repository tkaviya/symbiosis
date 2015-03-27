package net.blaklizt.symbiosis.sym_web.controllers;

import net.blaklizt.symbiosis.sym_authentication.authentication.Authenticator;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

@Controller
public class DefaultController
{
	@Autowired
	Authenticator authenticator;

	@Autowired UserDao userDao;

	Logger logger = Configuration.getNewLogger(DefaultController.class.getSimpleName());
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
			SymbiosisUserDetails userDetails = authenticator.loadUserByUsername(request.getParameter("username"));
			
			Date lastAccessDate = userDetails.getSymbiosisUser().getLastAccessDate();
			
			ResponseCode authResponse = authenticator.authenticateUser();
			
			
			if (authResponse == ResponseCode.SUCCESS)
			{
				logger.info("Authentiation successful.");
				JSONObject responseJSON = new JSONObject(ResponseCode.SUCCESS.toJSONResponse());
				responseJSON.put("auth_token", userDetails.getSymbiosisUser().getAuthToken());
				responseJSON.put("last_access_date", sdf.format(lastAccessDate));
				jsonResponse = responseJSON.toJSONString();
				
			}
			else
			{
				jsonResponse = ResponseCode.AUTHENTICATION_FAILED.toJSONResponse();
			}
		}
		catch (Exception ex)
		{
			logger.error("Failed to authenticate:\n" + ex.getMessage());
			jsonResponse = ResponseCode.GENERAL_ERROR.toJSONResponse();
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request)
	{
		String jsonResponse;
		try
		{
			logger.info("Got registration request.");



		}
		catch (Exception ex)
		{
			logger.error("Failed to authenticate: " + ex.getMessage());
			jsonResponse = ResponseCode.GENERAL_ERROR.toJSONResponse();
		}
		return jsonResponse;

		response.setStatus(HttpStatus.OK.value());

		logger.info("Returning response:\n" + jsonResponse);
		return jsonResponse;
	}
}
