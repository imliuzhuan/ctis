package com.ctis.service;

import com.ctis.entity.User;

public interface ILoginManager {
	/**
	 * 检查登录是否合法
	 * @param user
	 * @return
	 */
	User validLogin(User user);
}
