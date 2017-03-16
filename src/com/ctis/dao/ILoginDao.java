package com.ctis.dao;

import com.ctis.entity.User;

public interface ILoginDao {
	/**
	 * 检查登录是否合法
	 * @param user
	 * @return
	 */
	User validLogin(User user);
}
