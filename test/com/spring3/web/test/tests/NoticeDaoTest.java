package com.spring3.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring3.web.dao.Notice;
import com.spring3.web.dao.NoticeDao;
import com.spring3.web.dao.User;
import com.spring3.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/spring3/web/config/dao-context.xml",
		"classpath:com/spring3/web/config/security-context.xml",
		"classpath:com/spring3/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class NoticeDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("mamun", "Mamunur Rashid", "mamun123",
			"mamun@gmail.com", true, "ROLE_USER");
	private User user2 = new User("mizan", "Mizanur  Rahman", "mizan123",
			"mizan@gmail.com", true, "ROLE_USER");
	private User user3 = new User("mamun1", "Mamunur Rashid", "mamun123",
			"mamun1@gmail.com", true, "ROLE_USER");
	private User user4 = new User("mizan1", "Mizanur  Rahman", "mizan123",
			"mizan1@gmail.com", true, "ROLE_USER");

	private Notice notice1 = new Notice(user1,
			"Hi There I am User1, how are you?");
	private Notice notice2 = new Notice(user2,
			"Hi There I am User2, Where are you?");
	private Notice notice3 = new Notice(user3,
			"Hi There I am User3, Who are you?");
	private Notice notice4 = new Notice(user4,
			"Hi There I am User4, What is this?");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from notice");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrive() {
		userDao.createUser(user1);
		userDao.createUser(user2);
		userDao.createUser(user3);
		userDao.createUser(user4);

		noticeDao.saveOrUpdate(notice1);
		noticeDao.saveOrUpdate(notice2);
		noticeDao.saveOrUpdate(notice3);
		noticeDao.saveOrUpdate(notice4);

		List<Notice> notices = noticeDao.getNotices();
		assertEquals("Notices size should be 4", 4, notices.size());

	}

	@Test
	public void testGetUserName() {
		userDao.createUser(user1);
		userDao.createUser(user2);
		userDao.createUser(user3);
		userDao.createUser(user4);

		noticeDao.saveOrUpdate(notice1);
		noticeDao.saveOrUpdate(notice2);
		noticeDao.saveOrUpdate(notice3);
		noticeDao.saveOrUpdate(notice4);

	}
	
	@Test
	public void testUpdate(){
		userDao.createUser(user1);
		userDao.createUser(user2);
		userDao.createUser(user3);
		userDao.createUser(user4);

		noticeDao.saveOrUpdate(notice1);
		noticeDao.saveOrUpdate(notice2);
		noticeDao.saveOrUpdate(notice3);
		noticeDao.saveOrUpdate(notice4);
		
		notice3.setText("updated message fro notice3 check it>>");
		noticeDao.saveOrUpdate(notice3);
		
	}
	@Test
	public void testDelete(){
		userDao.createUser(user1);
		noticeDao.saveOrUpdate(notice1);
		noticeDao.deleteNotice(notice1.getId());
		
		List<Notice> empty = noticeDao.getNotices();

		assertEquals("Number of user should be 1", 0, empty.size());
		
	}

	@Test
	public void testNotice() {

		User user = new User("maruf", "Maruf Al", "maruf123",
				"maruf@gmail.com", true, "ROLE_USER");
		userDao.createUser(user);

		Notice notice = new Notice(user,
				"This is a test notice using unit test");
		noticeDao.saveOrUpdate(notice);

		List<Notice> notices = noticeDao.getNotices();

		assertEquals("Number of user should be 1", 1, notices.size());
		assertEquals("Created user should be identical or retrival user",
				notice, notices.get(0));

		Notice notice2 = new Notice(user,
				"This is a test notice using unit test");

		noticeDao.saveOrUpdate(notice2);
		notice2.setText("Messange has been changed");
		noticeDao.saveOrUpdate(notice2);

		List<Notice> userNotices = noticeDao.getNotices(user.getUsername());
		assertEquals("Number of user should be 1", 1, notices.size());
		
		
		
		
	}
	
	
	
}
