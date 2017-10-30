package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 227761 This OffersService class is for persistence
 */
@Component("offersDao")
public class OffersDao {
	Logger logger = LoggerFactory.getLogger(OffersDao.class);
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(@Autowired DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {
		logger.debug("Inside getOffers");
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				logger.debug("Outside getOffers");
				return offer;
			}

		});

	}

	public boolean update(Offer offer) {
		logger.debug("Inside  Update Offer");
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		logger.debug("Outside  Update Offer");
		return jdbc.update("update offers set name=:name, text=:text, email=:email where id=:id", params) == 1;
	}

	public boolean create(Offer offer) {
		logger.debug("Inside  create Offer");

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		logger.debug("Ouside  create Offer");
		return jdbc.update("insert into offers (name, text, email) values (:name, :text, :email)", params) == 1;

	}

	@Transactional
	public int[] create(List<Offer> offers) {
		logger.debug("Inside  create with List offers");
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		logger.debug("Outside  create with List offers");
		return jdbc.batchUpdate("insert into offers (id, name, text, email) values (:id, :name, :text, :email)",
				params);
	}

	public boolean delete(int id) {
		logger.debug("Inside  delete offers");
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		logger.debug("Outside  delete offers");
		return jdbc.update("delete from offers where id=:id", params) == 1;
	}

	public Offer getOffer(int id) {
		logger.debug("Inside  getOffer ");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from offers where id=:id", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				logger.debug("Ouside  getOffer ");
				return offer;
			}

		});
	}

	public Offer getOfferByName(String name) {
		logger.debug("Inside  getOfferByName ");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);

		return jdbc.queryForObject("select * from offers where name=:name", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				logger.debug("Ouside  getOfferByName ");
				return offer;
			}

		});
	}

}
