package org.renci.hearsay.web;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.renci.hearsay.dao.ParticipantDAO;
import org.renci.hearsay.dao.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ParticipantDAO participantDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws Exception {
        return "redirect:/login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() throws Exception {
        return "welcome";
    }

    @RequestMapping(value = "/carnac/carnac", method = RequestMethod.POST)
    public ModelAndView carnac(@ModelAttribute(value = "participant") ParticipantForm participant, BindingResult result)
            throws Exception {
        ModelAndView modelAndView = new ModelAndView("/carnac/carnac");
        modelAndView.addObject("participantName", participant.getParticipant().getName());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String participantJSON = mapper.writeValueAsString(participant.getParticipant());
        logger.info(participantJSON);
        modelAndView.addObject("participantJSON", participantJSON);

        return modelAndView;
    }

    @RequestMapping(value = "/carnac/participantList", method = RequestMethod.GET)
    public ModelAndView participantList() throws Exception {
        ModelAndView modelAndView = new ModelAndView("/carnac/participantList");
        List<Participant> participantList = new ArrayList<>();
        List<Participant> participants = participantDAO.findAll();
        if (CollectionUtils.isNotEmpty(participantList)) {
            participantList.addAll(participants);
        }

        if (CollectionUtils.isEmpty(participantList)) {
            participantList.add(new Participant("test"));
        }
        modelAndView.addObject("participantList", participantList);
        modelAndView.addObject("participant", new ParticipantForm());

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

    public ParticipantDAO getParticipantDAO() {
        return participantDAO;
    }

    public void setParticipantDAO(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

}
