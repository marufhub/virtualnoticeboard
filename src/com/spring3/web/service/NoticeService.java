package com.spring3.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring3.web.dao.Notice;
import com.spring3.web.dao.NoticeDao;

@Service("noticeService")
public class NoticeService {

	private NoticeDao noticeDAO;

	@Autowired
	public void setNoticeDAO(NoticeDao noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public List<Notice> getCurrent() {

		return noticeDAO.getNotices();

	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(Notice notice) {
		noticeDAO.saveOrUpdate(notice);
		System.out.println("notice is created.");

	}

	public Boolean hasNotice(String name) {

		if (name.equals(null))
			return false;
		List<Notice> notices = noticeDAO.getNotices(name);
		if (notices.size() == 0)
			return false;

		return true;
	}

	public Notice getNotice(String username) {
		List<Notice> notices = noticeDAO.getNotices(username);

		if (notices.size() == 0) {
			return null;
		}
		if (username == null) {
			return null;
		}

		return notices.get(0);
	}

	public void saveOrUpdate(Notice notice) {

		noticeDAO.saveOrUpdate(notice);

	}

	public void delete(int id) {
		noticeDAO.deleteNotice(id);

	}

}
