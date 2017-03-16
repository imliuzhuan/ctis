package com.ctis.service;

import java.util.List;
import java.util.Set;

import com.ctis.entity.Function;
import com.ctis.entity.Role;
import com.ctis.util.PageResult;

public interface IFunctionManager {
	/**
	 * 查询的时候按FUN_GROUP分组，按FUN_ORDER排序
	 * @param funcIds
	 * @return
	 */
	List<Function> getFunctions(Set<String> funcIds);

	/**
	 * 查询功能列表
	 * @param function
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(Function function, int page, int rows, String sort, String order);

	/**
	 * 增加功能
	 * @param function
	 */
	void add(Function function);

	/**
	 * 删除功能
	 * @param id
	 */
	void delete(String id);

	/**
	 * 修改功能
	 * @param function
	 */
	void modify(Function function);

	/**
	 * 根据ID数组获取功能列表
	 * @param funcIds
	 * @param page
	 * @param rows
	 * @return
	 */
	PageResult getFuncs(String[] funcIds, int page, int rows);

	/**
	 * 获取排除制定ID的功能列表
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 */
	PageResult getOtherFuncs(Role role, int page, int rows);
}
