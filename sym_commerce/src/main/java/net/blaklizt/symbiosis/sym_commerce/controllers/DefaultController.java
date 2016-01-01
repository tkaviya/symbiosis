package net.blaklizt.symbiosis.sym_commerce.controllers;

import net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator;
import net.blaklizt.symbiosis.sym_core_lib.CommonUtilities;
import net.blaklizt.symbiosis.sym_persistence.dao.implementation.SymbiosisUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tkaviya on 9/6/2015.
 */
@Controller
public class DefaultController {
    @Autowired
    SymbiosisAuthenticator symbiosisAuthenticator;

    @Autowired
    SymbiosisUserDaoImpl userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView test()
    {
        CommonUtilities.isNullOrEmpty("test string");
        ModelMap model = new ModelMap();
        return new ModelAndView("index.jsp", model);
    }
}
