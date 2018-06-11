package com.spring3.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring3.web.dao.User;
import com.spring3.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring3/web/config/dao-context.xml",
		"classpath:com/spring3/web/config/security-context.xml",
		"classpath:com/spring3/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}


	private User user1 = new User("mamun", "Mamunur Rashid", "mamun123",
			"mamun@gmail.com", true, "ROLE_USER");
	private User user2 = new User("mizan", "Mizanur  Rahman", "mizan123",
			"mizan@gmail.com", true, "ROLE_USER");
	private User user3 = new User("mamun1", "Mamunur Rashid", "mamun123",
			"mamun1@gmail.com", true, "ROLE_USER");
	private User user4 = new User("mizan1", "Mizanur  Rahman", "mizan123",
			"mizan1@gmail.com", true, "ROLE_USER");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from notice");
		jdbc.execute("delete from users");

	}

	@Test
	public void testCreateRetrive() {
		userDao.createUser(user1);

		List<User> users1 = userDao.getAllUsers();
		assertEquals("One user should be created and retrieved", 1,
				users1.size());

		assertEquals("Insert User should match Retrieved", user1, users1.get(0));

		userDao.createUser(user2);
		userDao.createUser(user3);
		userDao.createUser(user4);

		List<User> users2 = userDao.getAllUsers();
		assertEquals("Four user should be created and retrieved", 4,
				users2.size());

	}
	
	
	@Test
	public void testUserExists(){
		userDao.createUser(user1);
		userDao.createUser(user2);
		userDao.createUser(user3);
		assertTrue("User should exist", userDao.exists(user2.getUsername()));
		assertFalse("User Should not exist", userDao.exists("abc"));
	}

	@Test
	public void testCreateUser() {

		User user = new User("maruf", "Maruf Al", "maruf123",
				"maruf@gmail.com", true, "ROLE_USER");
		userDao.createUser(user);

		List<User> users = userDao.getAllUsers();

		assertEquals("Number of user should be 1", 1, users.size());
		assertTrue("User should exist", userDao.exists(user.getUsername()));
		assertFalse("User Should not exist", userDao.exists("abc"));
		assertEquals("Created user should be identical or retrival user", user,
				users.get(0));

	}

}
