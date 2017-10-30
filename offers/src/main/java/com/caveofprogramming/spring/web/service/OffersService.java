package com.caveofprogramming.spring.web.service;

import java.util.List;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;

/**
 * @author 227761 This OffersService interface is created for offer management
 */
public interface OffersService {

	void setOffersDao(OffersDao offersDao);

	List<Offer> getCurrent();

	void create(Offer offer);

}