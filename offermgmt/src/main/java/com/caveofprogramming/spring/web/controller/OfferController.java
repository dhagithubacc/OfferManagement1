package com.caveofprogramming.spring.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.caveofprogramming.spring.web.model.Offer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author 227761 This OfferController class is for managing offers
 */
@Controller
@PropertySource("classpath:validation.properties")
public class OfferController {
	Logger logger = LoggerFactory.getLogger(OfferController.class);

	@Value("${Size.offerForm.name}")
	private String sizeOfferFormName;

	@Value("${Duplicate.offerForm.name}")
	private String duplicateOfferFormName;

	@Value("${ValidEmail.offer.email}")
	private String validEmailOfferEmail;

	@Value("${NotBlank.offer.text}")
	private String notBlankOfferText;

	@LoadBalanced
	@Bean
	private RestTemplate restTemplate(RestTemplateBuilder builder) {

		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/createoffer", method = RequestMethod.GET)
	public String createOffer(Model model) {
		logger.debug("inside createOffer>>>>");
		model.addAttribute("offerForm", new Offer());

		return "createoffer";
	}

	@HystrixCommand(fallbackMethod = "notAbleToCreateOffer")
	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(@ModelAttribute("offerForm") Offer offerForm, Model model, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "name", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "email", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "text", "NotEmpty");

		if (bindingResult.hasErrors()) {

			if (offerForm.getName().length() < 8 || offerForm.getName().length() > 15) {

				model.addAttribute("name", sizeOfferFormName);
			}
			if (offerForm.getName().length() < 8 || offerForm.getName().length() > 15) {

				model.addAttribute("name", sizeOfferFormName);
			}
			if (offerForm.getEmail().equals(null) && offerForm.getEmail().isEmpty()) {
				model.addAttribute("email", validEmailOfferEmail);
			}

			if (offerForm.getText().equals(null) && offerForm.getText().isEmpty()) {
				model.addAttribute("text", notBlankOfferText);
			}

			return "createoffer";
		}

		logger.debug("inside new Offer>>>>");
		restTemplate.postForLocation(env.getProperty("offerservice.endpoint.postoffer"), offerForm);

		return "redirect:/offercreated";
	}

	@RequestMapping(value = "/offercreated", method = RequestMethod.GET)
	public String offerCreated(Model model) {
		logger.debug("Inside offercraeted>>>>>");
		return "offercreated";

	}

	@HystrixCommand(fallbackMethod = "notShowingOffers")
	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public String showOffers(Model model) {
		logger.debug("Instance-restTemplate>>>>>" + restTemplate);
		Offer[] offers = restTemplate.getForObject(env.getProperty("offerservice.endpoint.offerlist"), Offer[].class);

		model.addAttribute("offers", offers);

		return "offers";

	}

	public String notShowingOffers(Model model) {
		logger.debug("inside fallback notShowingOffers>>>>");

		return "servicedown";
	}

	public String notAbleToCreateOffer(@ModelAttribute("offerForm") Offer offerForm, Model model,
			BindingResult bindingResult) {
		logger.debug("inside fallback notAbleToCreateOffer>>>>");

		return "servicedown";
	}

}
