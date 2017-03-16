package com.ctis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.ICommentsDao;
import com.ctis.dao.ITopicDao;
import com.ctis.entity.Comments;
import com.ctis.entity.Topic;
import com.ctis.service.ITopicManager;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;
import com.ctis.vo.TopicVO;

@Service
@Transactional
public class TopicManagerImpl implements ITopicManager {
	@Resource
	private ITopicDao topicDao;
	@Resource
	private ICommentsDao commentsDao;

	public PageResult list(Topic topic, String dateFrom, String dateTo, int page, int rows, String sort, String order) {
		PageResult pageResult = topicDao.list(topic, dateFrom, dateTo, page, rows, sort, order);
		return pageResult;
	}

	public Topic getTopic(String id) {
		if (!StringUtil.isNullStr(id)) {
			Topic topic = topicDao.getTopic(id);
			return topic;
		}
		return null;
	}

	public void add(Topic topic) {
		if (topic != null) {
			topicDao.add(topic);
		}
	}

	public void delete(String id) {
		if (!StringUtil.isNullStr(id)) {
			topicDao.delete(id);
		}
	}

	public void modify(Topic topic) {
		if (topic != null && !StringUtil.isNullStr(topic.getId())) {
			topicDao.modify(topic);
		}
	}

	public TopicVO getTopicVO(String id) {
		if (!StringUtil.isNullStr(id)) {
			Topic topic = getTopic(id);
			List<Comments> comments = commentsDao.getCommentsByTopicId(id);
			return new TopicVO(topic, comments);
		}
		return null;
	}
}
