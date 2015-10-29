package org.renci.hearsay.web;

import org.renci.hearsay.dao.ChromosomeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ChromosomeDAO chromosomeDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(value = "/carnac", method = RequestMethod.GET)
    public ModelAndView carnac() throws Exception {
        ModelAndView modelAndView = new ModelAndView("carnac");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }

    public ChromosomeDAO getChromosomeDAO() {
        return chromosomeDAO;
    }

    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO) {
        this.chromosomeDAO = chromosomeDAO;
    }

}
