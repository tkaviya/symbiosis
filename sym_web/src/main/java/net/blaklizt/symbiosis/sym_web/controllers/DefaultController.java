package net.blaklizt.symbiosis.sym_web.controllers;

import net.blaklizt.symbiosis.sym_authentication.authentication.Authenticator;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_persistence.dao.UserDao;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class DefaultController
{
	@Autowired
	Authenticator authenticator;

	@Autowired UserDao userDao;

	Logger logger = Configuration.getNewLogger(DefaultController.class.getSimpleName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root()
    {
        ModelMap model = new ModelMap();
        return new ModelAndView("index.jsp", model);
    }

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(@RequestBody String postData)
	{
		try
		{
			logger.info("Got authentication request:\n" + postData);
			JSONObject authRequest = new JSONObject(postData);
			UserDetails userDetails = authenticator.loadUserByUsername(authRequest.getString("username"));
			String jsonResponse = null;
			if (userDetails.getPassword().equals(authRequest.getString("password")))
			{
				logger.info("Authentiation successful.");
				jsonResponse = ResponseCode.SUCCESS.toJSONResponse();
			}
			else
			{
				jsonResponse = ResponseCode.AUTHENTICATION_FAILED.toJSONResponse();
			}
			logger.info("Returning response:" + jsonResponse);
			return jsonResponse;
		}
		catch (Exception ex)
		{
			logger.error("Failed to authenticate: " + ex.getMessage());
			return ResponseCode.GENERAL_ERROR.toJSONResponse();
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
	}
}
