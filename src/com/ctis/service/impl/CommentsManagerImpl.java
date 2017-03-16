package com.ctis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.ICommentsDao;
import com.ctis.entity.Comments;
import com.ctis.service.ICommentsManager;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Service
@Transactional
public class CommentsManagerImpl implements ICommentsManager {
	@Resource
	private ICommentsDao commentsDao;

	public PageResult list(Comments comments, String dateFrom, String dateTo, int page, int rows, String sort,
			String order) {
		PageResult pageResult = commentsDao.list(comments, dateFrom, dateTo, page, rows, sort, order);
		return pageResult;
	}

	public void deleteMore(String[] ids) {
		if (ids != null && ids.length > 0) {
			commentsDao.deleteMore(ids);
		}
	}

	public void add(Comments comments) {
		if (comments != null) {
			commentsDao.add(comments);
		}
	}

	public Comments oper(String id, String flag) {
		if (!StringUtil.isNullStr(id) && !StringUtil.isNullStr(flag)) {
			return commentsDao.oper(id, flag);
		}
		return null;
	}

	public void check(String id, String flag) {
		if (!StringUtil.isNullStr(id) && !StringUtil.isNullStr(flag)) {
			commentsDao.check(id, flag);
		}
	}

	public void delete(String id) {
		if (!StringUtil.isNullStr(id)) {
			commentsDao.delete(id);
		}
	}

	public void checkMore(String[] ids, String state) {
		if (ids != null && ids.length > 0 && !StringUtil.isNullStr(state)) {
			commentsDao.checkMore(ids, state);
		}
	}
}
