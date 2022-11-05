package com.nilx.springboot.web.bugtracker.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nilx.springboot.web.bugtracker.model.Jira;
import com.nilx.springboot.web.bugtracker.repository.BugTrackerRepository;

@Controller
public class BugTrackerController {
	private static final Logger logger = LoggerFactory.getLogger(BugTrackerController.class);
	@Autowired
	BugTrackerRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-jiras", method = RequestMethod.GET)
	public String showJiras(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("jiras", repository.findByUser(name));
		return "list-jiras";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	@RequestMapping(value = "/add-jira", method = RequestMethod.GET)
	public String showAddJiraPage(ModelMap model) {
		model.addAttribute("jira", new Jira(0, getLoggedInUserName(model),
				"Default Desc", new Date(), false));
		return "jira";
	}

	@RequestMapping(value = "/delete-jira", method = RequestMethod.GET)
	public String deleteJira(@RequestParam int id) {

		repository.deleteById(id);
		return "redirect:/list-jiras";
	}

	@RequestMapping(value = "/update-jira", method = RequestMethod.GET)
	public String showUpdateJiraPage(@RequestParam int id, ModelMap model) {
		Jira jira = repository.findById(id).get();
		model.put("jira", jira);
		return "jira";
	}

	@RequestMapping(value = "/update-jira", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Jira jira,
			BindingResult result) {

		if (result.hasErrors()) {
			return "jira";
		}

		jira.setUser(getLoggedInUserName(model));

		repository.save(jira);
		return "redirect:/list-jiras";
	}

	@RequestMapping(value = "/add-jira", method = RequestMethod.POST)
	public String addJira(ModelMap model, @Valid Jira jira, BindingResult result) {

		logger.info("#### Jira model is: "+ jira);
		if (result.hasErrors()) {
			return "jira";
		}

		jira.setUser(getLoggedInUserName(model));
		repository.save(jira);
		return "redirect:/list-jiras";
	}

}
