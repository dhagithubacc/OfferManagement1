package com.caveofprogramming.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.model.User;

/**
 * @author 227761 This UserRepository interface handles for user persistence
 */
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Override
	@Transactional
	void delete(User user);

}
