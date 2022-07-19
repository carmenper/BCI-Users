package com.cemp.bci.users;

import com.cemp.bci.users.controller.UserController;
import com.cemp.bci.users.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BciUsersApplicationTests {

	@Autowired
	private UserController userController;

	@Autowired
	private UserServiceImpl userService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(userController);
		Assertions.assertNotNull(userService);
	}

}
