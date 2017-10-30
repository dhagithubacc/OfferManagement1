package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;

/**
 * @author 227761 This OffersService class is for offer management
 */
@Service("offersService")
public class OffersServiceImpl implements OffersService {

	private OffersDao offersDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.caveofprogramming.spring.web.service.OffersService#setOffersDao(com.
	 * caveofprogramming.spring.web.dao.OffersDao)
	 */
	@Override
	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.caveofprogramming.spring.web.service.OffersService#getCurrent()
	 */
	@Override
	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.caveofprogramming.spring.web.service.OffersService#create(com.
	 * caveofprogramming.spring.web.dao.Offer)
	 */
	@Override
	public void create(Offer offer) {
		offersDao.create(offer);
	}
}
