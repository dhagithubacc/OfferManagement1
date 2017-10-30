package com.caveofprogramming.spring.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.service.SecurityService;
import com.caveofprogramming.spring.web.service.UserService;

/**
 * @author 227761 This UserController class is for managing users
 */
@Controller
@PropertySource("classpath:validation.properties")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${Size.userForm.username}")
	private String sizeUserFormUserName;

	@Value("${Duplicate.userForm.username}")
	private String duplicateUserFormUserName;

	@Value("${Size.userForm.password}")
	private String sizeUserFormPassword;

	@Value("${Diff.userForm.passwordConfirm}")
	private String diffUserFormPasswordConfirm;

	@Value("${ValidEmail.user.email}")
	private String validEmailUserEmail;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/newaccount", method = RequestMethod.GET)
	public String registration(Model model) {
		logger.debug("inside new account before add attribute>>>>");
		model.addAttribute("userForm", new User());
		logger.debug("inside new account after addattribute>>>>");
		return "newaccount";
	}

	@RequestMapping(value = "/newaccount", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		logger.debug("inside new account>>>>");
		logger.debug("before password >>>>" + userForm.getPassword());
		logger.debug("before email >>>>" + userForm.getEmail());
		logger.debug("before confirmpassword >>>>" + userForm.getPasswordConfirm());

		// userValidator.validate(userForm, bindingResult);
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "password", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "passwordConfirm", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "email", "NotEmpty");

		if (bindingResult.hasErrors()) {
			if (userForm.getUsername().length() < 8 || userForm.getUsername().length() > 15) {

				model.addAttribute("username", sizeUserFormUserName);
			}

			if (userService.findByUsername(userForm.getUsername()) != null) {

				model.addAttribute("username", duplicateUserFormUserName);
			}
			if (userForm.getPassword().length() < 8 || userForm.getPassword().length() > 15) {
				model.addAttribute("password", sizeUserFormPassword);
			}

			if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
				model.addAttribute("passwordConfirm", diffUserFormPasswordConfirm);
			}

			if (userForm.getEmail().equals(null) && userForm.getEmail().isEmpty()) {
				model.addAttribute("email", validEmailUserEmail);
			}

			return "newaccount";
		}
		logger.debug("inside new account>>>>");
		userForm.setEnabled(true);

		logger.debug("Before  save new account>>>>");
		userService.save(userForm);

		logger.debug("after save new account>>>>");
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		logger.debug("after autologin new account>>>>");

		return "redirect:/accountcreated";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		logger.debug("Inside login>>>>>");
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		logger.debug("outside login method>>>>>");
		return "login";
	}

	@RequestMapping(value = "/accountcreated", method = RequestMethod.GET)
	public String accountCreated(Model model) {
		logger.debug("Inside accountcreated>>>>>");
		return "accountcreated";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		logger.debug("Inside welcome>>>>>");

		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String exit(Model model) {
		logger.debug("Inside logout>>>>>");
		return "login";

	}

	@RequestMapping(value="/admin",method = RequestMethod.GET)
	public String showAdmin(Model model) {

		List<User> users = userService.getAllUsers();
		
		model.addAttribute("users", users);

		return "admin";
	}

}
