package ttt.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import ttt.model.GameWithAI;
import ttt.model.SavedSteps;
import ttt.model.Users;

@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	UserDao userDao;

	Users u = new Users();

	@Test
	public void getUser() {
		assert userDao.getUsers("cysun").getUsername()
				.equalsIgnoreCase("cysun");
	}


}