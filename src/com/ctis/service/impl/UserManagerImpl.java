package com.ctis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.IUserDao;
import com.ctis.entity.User;
import com.ctis.service.IUserManager;
import com.ctis.util.Constant;
import com.ctis.util.DateUtil;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Service
@Transactional
public class UserManagerImpl implements IUserManager {
	@Resource
	private IUserDao userDao;

	public boolean check(User user) {
		if (null != user) {
			return userDao.check(user);
		}
		return false;
	}

	public boolean add(User user) {
		if (null != user) {
			user.setId(Constant.getSeries());
			if (StringUtil.isNullStr(user.getPassword())) {
				user.setPassword("1234");
			}
			//2表示普通用户
			user.setRoleIds(StringUtil.isNullStr(user.getRoleIds()) ? "2" : user.getRoleIds().replaceAll("\\s*", ""));
			user.setCreateTime(DateUtil.getCurrentTimeString());
			user.setIsSys("0");
			return userDao.add(user);
		}
		return false;
	}

	public User modify(User user) {
		if (user != null && !StringUtil.isNullStr(user.getId())) {
			user.setRoleIds(StringUtil.isNullStr(user.getRoleIds()) ? "" : user.getRoleIds().replaceAll("\\s*", ""));
			return userDao.modify(user);
		}
		return null;
	}

	public boolean delete(User user) {
		if (null != user) {
			return userDao.delete(user);
		}
		return false;
	}

	public PageResult list(User user, int page, int rows, String sort, String order) {
		PageResult pageResult = userDao.list(user, page, rows, sort, order);
		return pageResult;
	}

	@Override
	public User getUserById(String id) {
		if (!StringUtil.isNullStr(id)) {
			return userDao.getUserById(id);
		}
		return null;
	}
}
