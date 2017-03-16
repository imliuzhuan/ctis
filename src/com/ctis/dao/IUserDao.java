package com.ctis.dao;

import com.ctis.entity.User;
import com.ctis.util.PageResult;

public interface IUserDao {
	/**
	 * 检查用户名是否重复
	 * @param user
	 * @return
	 */
	boolean check(User user);

	/**
	 * 查询用户列表
	 * @param user
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(User user, int page, int rows, String sort, String order);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	boolean add(User user);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	User modify(User user);

	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	boolean delete(User user);

	/**
	 * 根据ID获取用户信息
	 * @param id
	 * @return
	 */
	User getUserById(String id);
}
