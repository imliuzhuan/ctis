package com.ctis.dao;

import java.io.Serializable;
import java.util.List;

import com.ctis.exception.DAOException;
import com.ctis.util.PageResult;

public interface IHibernateBaseDao {
	/**
	 * 创建对象
	 * @param o
	 * @throws DAOException
	 */
	public void create(Object o) throws DAOException;

	/**
	 * 更新对象
	 * @param o
	 * @throws DAOException
	 */
	public void update(Object o) throws DAOException;

	/**
	 * 删除对象
	 * @param o
	 * @throws DAOException
	 */
	public void delete(Object o) throws DAOException;

	/**
	 * 删除指定id 的对象记录
	 * @param clazz
	 * @param id
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public void delete(Class clazz, Serializable id) throws DAOException;

	/**
	 * 清空表格数据
	 * @param clazz
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public void deleteAll(Class clazz) throws DAOException;

	/**
	 * 根据id获得对象
	 * @param clazz
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public Object get(Class clazz, Serializable id) throws DAOException;

	/**
	 * 查询，返回对象列表
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql) throws DAOException;
	/**
	 * 返回符合条件翻页记录，返回记录类型为PageResult
	 * @param queryString 查询条件
	 * @param page 当前页码
	 * @param rows 总行数
	 * @return
	 * @throws DAOException
	 */
	public PageResult findPageResult(final String queryString, final int page, final int rows) throws DAOException;
}
