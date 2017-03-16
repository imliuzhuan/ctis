package com.ctis.dao.impl;

import org.springframework.stereotype.Repository;

import com.ctis.dao.ITopicDao;
import com.ctis.entity.Topic;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class TopicDaoImpl extends HibernateBaseDaoImpl implements ITopicDao {
	public PageResult list(Topic topic, String dateFrom, String dateTo, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Topic t where 1=1 ");
		if (topic != null && !StringUtil.isNullStr(topic.getName())) {
			hql.append(" and t.name like '%").append(topic.getName()).append("%' ");
		}
		if (!StringUtil.isNullStr(dateFrom)) {
			hql.append(" and t.lastModifyTime >= '").append(dateFrom).append("' ");
		}
		if (!StringUtil.isNullStr(dateTo)) {
			hql.append(" and (t.lastModifyTime <= '").append(dateTo).append("' ");
			hql.append(" or t.lastModifyTime like '").append(dateTo).append("%') ");
		}
		if (topic != null && !StringUtil.isNullStr(topic.getLastModifyTime())) {
			hql.append(" and t.lastModifyTime like '").append(topic.getLastModifyTime()).append("%' ");
		}
		if (!StringUtil.isNullStr(topic.getState())) {
			hql.append(" and t.state = '").append(topic.getState()).append("' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by t.lastModifyTime desc ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public Topic getTopic(String id) {
		Topic topic = (Topic) get(Topic.class, id);
		return topic;
	}

	public void add(Topic topic) {
		this.create(topic);
	}

	public void delete(String id) {
		this.delete(Topic.class, id);
	}

	public void modify(Topic topic) {
		Topic exist = (Topic) this.get(Topic.class, topic.getId());
		if (exist != null) {
			if (!StringUtil.isNullStr(topic.getName())) {
				exist.setName(topic.getName());
			}
			if (!StringUtil.isNullStr(topic.getContent())) {
				exist.setContent(topic.getContent());
			}
			if (!StringUtil.isNullStr(topic.getState())) {
				exist.setState(topic.getState());
			}
			if (!StringUtil.isNullStr(topic.getLastModifyTime())) {
				exist.setLastModifyTime(topic.getLastModifyTime());
			}
		}
		this.update(exist);
	}
}
