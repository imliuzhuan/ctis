package com.ctis.service;

import java.util.List;

import com.ctis.entity.Role;
import com.ctis.util.PageResult;

public interface IRoleManager {
	/**
	 * 根据角色ID数组查询角色数据
	 * @param roleIds
	 * @return
	 */
	List<Role> getRoles(String[] roleIds);

	/**
	 * 查询角色列表
	 * @param role
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(Role role, int page, int rows, String sort, String order);

	/**
	 * 增加角色
	 * @param role
	 */
	void add(Role role);

	/**
	 * 删除角色
	 * @param id
	 */
	void delete(String id);

	/**
	 * 修改角色
	 * @param role
	 */
	void modify(Role role);

	/**
	 * 获取所有角色数据
	 * @return
	 */
	List<Role> getRoles();
}
