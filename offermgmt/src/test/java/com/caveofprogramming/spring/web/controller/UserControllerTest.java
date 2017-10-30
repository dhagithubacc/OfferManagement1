package com.caveofprogramming.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void testRegistrationSuccess() throws Exception {

		mockMvc.perform(post("/newaccount").param("username", "Test0005").param("email", "ddhara10@gmail.com")
				.param("password", "Test0005").param("passwordConfirm", "Test0005")

		).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/accountcreated"));

		user = userRepository.findByUsername("Test0005");
		userRepository.delete(user);
	}

	@Test
	public void testAccountCreatedSuccess() throws Exception {

		mockMvc.perform(get("/accountcreated")

		).andExpect(status().isOk()).andExpect(view().name("accountcreated"));

	}

	@Test
	public void testShowAdminSuccess() throws Exception {

		mockMvc.perform(get("/admin")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("admin"));
	}

	@Test
	public void testLogoutSuccess() throws Exception {

		mockMvc.perform(get("/logout")

		)

				.andExpect(status().isOk())

				.andExpect(view().name("login"));
	}

}
