package com.ctis.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Comments;
import com.ctis.entity.User;
import com.ctis.service.ICommentsManager;
import com.ctis.util.Constant;
import com.ctis.util.DateUtil;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Controller
@Scope("prototype")
public class CommentsAction extends BaseAction<Comments> {
	private static final long serialVersionUID = 47L;
	@Resource
	private ICommentsManager commentsManager;
	public String dateFrom;
	public String dateTo;
	public String[] ids;
	public String flag;

	public String list() {
		PageResult pageResult = commentsManager.list(actionForm, dateFrom, dateTo, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String myList() {
		User user = (User) request.getSession().getAttribute("user");
		actionForm.setCreator(user == null || StringUtil.isNullStr(user.getId()) ? "__" : user.getId());
		PageResult pageResult = commentsManager.list(actionForm, dateFrom, dateTo, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String deleteMore() {
		commentsManager.deleteMore(ids);
		renderJSON("ok");
		return SUCCESS;
	}

	public String add() {
		actionForm.setId(Constant.getSeries());
		User user = (User) request.getSession().getAttribute("user");
		actionForm.setCreator(user.getId());
		actionForm.setCreateTime(DateUtil.getCurrentTimeString());
		actionForm.setState("0");
		actionForm.setPraise(0);
		actionForm.setTread(0);
		commentsManager.add(actionForm);
		renderJSON("ok");
		return SUCCESS;
	}

	public String oper() {
		Comments comments = commentsManager.oper(actionForm.getId(), flag);
		renderJSON(comments);
		return SUCCESS;
	}

	public String check() {
		commentsManager.check(actionForm.getId(), flag);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		commentsManager.delete(actionForm.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String checkMore() {
		commentsManager.checkMore(ids, actionForm.getState());
		renderJSON("ok");
		return SUCCESS;
	}
}
