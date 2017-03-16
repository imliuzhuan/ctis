package com.ctis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.ILoginDao;
import com.ctis.entity.User;

@Repository
public class LoginDaoImpl extends HibernateBaseDaoImpl implements ILoginDao {
	@SuppressWarnings("unchecked")
	public User validLogin(User user) {
		Query query = currentSession().createQuery("from User u where u.nickName = :nickName and u.password = :password");
		query.setParameter("nickName", user.getNickName());
		query.setParameter("password", user.getPassword());
		List<User> users = query.list();
		if (null != users && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
}
