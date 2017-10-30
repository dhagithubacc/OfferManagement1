package com.caveofprogramming.spring.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

/**
 * @author 227761 This OffersController class is for managing offers
 */
@RestController
@RequestMapping(value = "/offers")
public class OffersController {
	Logger logger = LoggerFactory.getLogger(OffersController.class);
	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping(method = RequestMethod.GET,value="/offerlist")
	// @ResponseBody
	public List<Offer> showOffers() {
		logger.debug("Inside OffersController showOffers ");
		List<Offer> offers = offersService.getCurrent();
		logger.debug("Outside OffersController showOffers " + offers.size());
		logger.debug("Outside OffersController showOffers " + offers);
		return offers;
	}

	@RequestMapping(method = RequestMethod.POST,value="/postoffer")
	public String create(@RequestBody Offer offerForm) {
		logger.debug("create Offer");
		offersService.create(offerForm);
		logger.debug("After Offer Service");

		return "Created :" + offerForm.getId();
	}

}
