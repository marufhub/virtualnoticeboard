package com.spring3.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("userDAO")
@Transactional
public class UserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void createUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);

	}

	public boolean exists(String username) {

		Criteria crit = session().createCriteria(User.class);
		// crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.idEq(username));
		User user = (User) crit.uniqueResult();
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		return session().createQuery("from User").list();

	}

}
