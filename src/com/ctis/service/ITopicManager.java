package com.ctis.service;

import com.ctis.entity.Topic;
import com.ctis.util.PageResult;
import com.ctis.vo.TopicVO;

public interface ITopicManager {
	/**
	 * 查询话题列表
	 * @param topic
	 * @param dateFrom
	 * @param dateTo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(Topic topic, String dateFrom, String dateTo, int page, int rows, String sort, String order);

	/**
	 * 根据ID获取话题
	 * @param id
	 * @return
	 */
	Topic getTopic(String id);

	/**
	 * 增加话题
	 * @param topic
	 */
	void add(Topic topic);

	/**
	 * 根据ID删除话题
	 * @param id
	 */
	void delete(String id);

	/**
	 * 修改话题
	 * @param topic
	 */
	void modify(Topic topic);

	/**
	 * 根据话题id获取TopicVO类
	 * @param id
	 * @return
	 */
	TopicVO getTopicVO(String id);
}
