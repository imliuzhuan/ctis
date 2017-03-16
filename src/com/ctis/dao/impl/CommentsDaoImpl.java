package com.ctis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.ICommentsDao;
import com.ctis.entity.Comments;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class CommentsDaoImpl extends HibernateBaseDaoImpl implements ICommentsDao {
	public PageResult list(Comments comments, String dateFrom, String dateTo, int page, int rows, String sort,
			String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Comments c where 1=1 ");
		if (!StringUtil.isNullStr(dateFrom)) {
			hql.append(" and c.createTime >= '").append(dateFrom).append("' ");
		}
		if (!StringUtil.isNullStr(dateTo)) {
			hql.append(" and (c.createTime <= '").append(dateTo).append("' ");
			hql.append(" or c.createTime like '").append(dateTo).append("%') ");
		}
		if (comments != null && !StringUtil.isNullStr(comments.getCreator())) {
			hql.append(" and c.creator = '").append(comments.getCreator()).append("' ");
		}
		if (comments != null && !StringUtil.isNullStr(comments.getState())) {
			hql.append(" and c.state = '").append(comments.getState()).append("' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by c.createTime desc ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public void deleteMore(String[] ids) {
		for (String id : ids) {
			this.delete(Comments.class, id);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Comments> getCommentsByTopicId(String id) {
		Query query = currentSession().createQuery("from Comments c where c.topicId = :topicId and c.state='2' order by c.createTime asc ");
		query.setParameter("topicId", id);
		return query.list();
	}

	public void add(Comments comments) {
		this.create(comments);
	}

	public Comments oper(String id, String flag) {
		Comments comments = (Comments) get(Comments.class, id);
		if (comments != null) {
			if ("praise".equals(flag)) {
				comments.setPraise(comments.getPraise() + 1);
			}
			if ("tread".equals(flag)) {
				comments.setTread(comments.getTread() + 1);
			}
		}
		this.update(comments);
		return comments;
	}

	public void check(String id, String flag) {
		Comments comments = (Comments) get(Comments.class, id);
		if (comments != null) {
			if ("agree".equals(flag)) {
				comments.setState("2");
			}
			if ("disagree".equals(flag)) {
				comments.setState("1");
			}
		}
		this.update(comments);
	}

	public void delete(String id) {
		this.delete(Comments.class, id);
	}

	public void checkMore(String[] ids, String state) {
		Query query = currentSession().createQuery("update Comments c set c.state = :state where c.id in (:ids) ");
		query.setParameter("state", state);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}
}
