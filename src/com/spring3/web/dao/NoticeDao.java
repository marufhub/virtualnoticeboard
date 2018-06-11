package com.spring3.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component("noticesDAO")
@Transactional
public class NoticeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	
	@SuppressWarnings("unchecked")
	public List<Notice> getNotices() {

		Criteria crit = session().createCriteria(Notice.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Notice> getNotices(String username) {

		Criteria crit = session().createCriteria(Notice.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));

		return crit.list();
		

	}

	// Need to implement using hibernate Myself
	// @Transactional
	// public int[] createNotice(List<Notice> notices) {
	//
	// SqlParameterSource[] params = SqlParameterSourceUtils
	// .createBatch(notices.toArray());
	//
	// return jdbc
	// .batchUpdate(
	// "insert into notice (username, text) values (:username, :text)",
	// params);
	// }

	public void saveOrUpdate(Notice notice) {

		session().saveOrUpdate(notice);
	}

	public boolean deleteNotice(int id) {

		Query query = session().createQuery("delete from Notice where id =:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	public Notice getNotice(int id) {

		Criteria crit = session().createCriteria(Notice.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));

		return (Notice)crit.uniqueResult();
	}

}
