package com.ctis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.IUserDao;
import com.ctis.entity.User;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class UserDaoImpl extends HibernateBaseDaoImpl implements IUserDao {
	@SuppressWarnings("unchecked")
	public boolean check(User user) {
		Query query = currentSession().createQuery("from User u where u.nickName = :nickName");
		query.setParameter("", user.getNickName());
		List<User> users = query.list();
		if (null != users && users.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean add(User user) {
		try {
			this.create(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public User modify(User user) {
		try {
			User user2 = (User) this.get(User.class, user.getId());
			if (user2 != null) {
				if (!StringUtil.isNullStr(user.getUserName())) {
					user2.setUserName(user.getUserName());
				}
				if (!StringUtil.isNullStr(user.getNickName())) {
					user2.setNickName(user.getNickName());
				}
				if (!StringUtil.isNullStr(user.getTelephone())) {
					user2.setTelephone(user.getTelephone());
				}
				if (!StringUtil.isNullStr(user.getEmail())) {
					user2.setEmail(user.getEmail());
				}
				if (!StringUtil.isNullStr(user.getGender())) {
					user2.setGender(user.getGender());
				}
				if (!StringUtil.isNullStr(user.getRoleIds())) {
					user2.setRoleIds(user.getRoleIds());
				}
				if (!StringUtil.isNullStr(user.getPassword())) {
					user2.setPassword(user.getPassword());
				}
				this.update(user2);
			}
			return user2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(User user) {
		try {
			this.delete(User.class, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PageResult list(User user, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from User u where 1=1 ");
		if (user != null && !StringUtil.isNullStr(user.getNickName())) {
			hql.append(" and u.nickName like '%").append(user.getNickName()).append("%' ");
		}
		if (user != null && !StringUtil.isNullStr(user.getUserName())) {
			hql.append(" and u.userName like '%").append(user.getUserName()).append("%' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by u.createTime ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public User getUserById(String id) {
		User user = (User) this.get(User.class, id);
		return user;
	}
}
