package net.blaklizt.symbiosis.sym_web.controllers;

import net.blaklizt.symbiosis.sym_authentication.authentication.Authenticator;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_common.response.ResponseCode;
import net.blaklizt.symbiosis.sym_common.utilities.CommonUtilities;
import net.blaklizt.symbiosis.sym_common.utilities.Validator;
import net.blaklizt.symbiosis.sym_persistence.User;
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
import java.util.Date;


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

				 if (!Validator.isValidUsername(request.getParameter("username")))      {   jsonResponse = ResponseCode.INVALID_USERNAME.toJSONResponse();  }
			else if (!Validator.isValidPassword(request.getParameter("password")))      {   jsonResponse = ResponseCode.INVALID_PASSWORD.toJSONResponse();  }
			else if (request.getParameter("email") != null && !Validator.isValidEmailAddress(request.getParameter("email")))
			{
				jsonResponse = ResponseCode.INVALID_EMAIL.toJSONResponse();
			}
			else if (request.getParameter("msisdn") != null && !Validator.isValidMsisdn(request.getParameter("msisdn")))
			{
				jsonResponse = ResponseCode.INVALID_MSISDN.toJSONResponse();
			}
			else if (request.getParameter("firstname") != null && !Validator.isValidFirstName(request.getParameter("firstname")))
			{
				jsonResponse = ResponseCode.INVALID_FIRST_NAME.toJSONResponse();
			}
			else if (request.getParameter("lastname") != null && !Validator.isValidLastName(request.getParameter("lastname")))
			{
				jsonResponse = ResponseCode.INVALID_LAST_NAME.toJSONResponse();
			}
			else if (request.getParameter("name") != null && !Validator.isValidLastName(request.getParameter("name")))
			{
				jsonResponse = ResponseCode.INVALID_NAME.toJSONResponse();
			}
			else
			{
				//data is valid. attempt registration
				if (userDao.findByUsername(request.getParameter("username")) != null)
				{
					jsonResponse = ResponseCode.PREVIOUS_REGISTRATION_FOUND.toJSONResponse();
				}
				else if (userDao.findByEmail(request.getParameter("email")) != null)
				{
					jsonResponse = ResponseCode.PREVIOUS_EMAIL_FOUND.toJSONResponse();
				}
				else if (userDao.findByMsisdn(request.getParameter("msisdn")) != null)
				{
					jsonResponse = ResponseCode.PREVIOUS_MSISDN_FOUND.toJSONResponse();
				}
				else
				{
					//no existing user/invalid data. registration can proceed
					User newUser = new User();

					//if name is passed, use that as name
					newUser.setName(request.getParameter("name") != null ? request.getParameter("name") : null);

					//if name is not passed, use firstname or lastname or both
					if (newUser.getName() == null && (request.getParameter("Firstname") != null || request.getParameter("Lastname") != null))
					{
						String name = request.getParameter("Firstname");
						if (CommonUtilities.isNullOrEmpty(name))
						{
							name = request.getParameter("Lastname");
						}
						else if (!CommonUtilities.isNullOrEmpty(request.getParameter("Lastname")))
						{
							name += " " + request.getParameter("Lastname");
						}
						newUser.setName(name);
					}

					newUser.setUsername(request.getParameter("username"));
					newUser.setPassword(request.getParameter("password"));
					newUser.setEmail(request.getParameter("email"));
					newUser.setLastLoginDate(new Date());

					ResponseCode registrationResponse = authenticator.register(newUser);

					jsonResponse = registrationResponse.toJSONResponse();

					if (registrationResponse == ResponseCode.SUCCESS)
					{
						logger.info("Authentiation successful.");
						JSONObject registrationData = new JSONObject(jsonResponse);
						registrationData.put("auth_token", newUser.getAuthToken());
						jsonResponse = registrationData.toString();
					}
					logger.info("Returning response:" + jsonResponse);
				}
			}

		}
		catch (Exception ex)
		{
			logger.error("Failed to authenticate: " + ex.getMessage());
			jsonResponse = ResponseCode.GENERAL_ERROR.toJSONResponse();
		}
		return jsonResponse;
	}
}
