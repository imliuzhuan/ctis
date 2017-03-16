package com.ctis.service;

import com.ctis.entity.Comments;
import com.ctis.util.PageResult;

public interface ICommentsManager {
	/**
	 * 获取评论列表
	 * @param comments
	 * @param dateFrom
	 * @param dateTo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(Comments comments, String dateFrom, String dateTo, int page, int rows, String sort, String order);

	/**
	 * 根据ID删除评论
	 * @param ids
	 */
	void deleteMore(String[] ids);

	/**
	 * 增加一条评论
	 * @param comments
	 */
	void add(Comments comments);

	/**
	 * 支持或反对
	 * @param id
	 * @param flag
	 * @return
	 */
	Comments oper(String id, String flag);

	/**
	 * 审核
	 * @param id
	 * @param flag
	 */
	void check(String id, String flag);

	/**
	 * 根据id删除评论
	 * @param id
	 */
	void delete(String id);

	/**
	 * 
	 * @param ids
	 * @param state
	 */
	void checkMore(String[] ids, String state);
}
