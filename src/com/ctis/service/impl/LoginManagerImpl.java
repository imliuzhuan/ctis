package com.ctis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.ILoginDao;
import com.ctis.entity.User;
import com.ctis.service.ILoginManager;
import com.ctis.util.StringUtil;
@Service
@Transactional
public class LoginManagerImpl implements ILoginManager {
	@Resource
	private ILoginDao loginDao;

	public User validLogin(User user) {
		if (null != user && !StringUtil.isNullStr(user.getNickName()) && !StringUtil.isNullStr(user.getPassword())) {
			return loginDao.validLogin(user);
		}
		return null;
	}
}
