package com.ctis.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.User;
import com.ctis.service.IUserManager;
import com.ctis.util.PageResult;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 47L;
	@Resource
	private IUserManager userManager;
	User user = actionForm;

	public String list() {
		PageResult pageResult = userManager.list(user, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String add() {
		userManager.add(user);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		userManager.delete(user);
		renderJSON("ok");
		return SUCCESS;
	}

	public String modify() {
		userManager.modify(user);
		renderJSON("ok");
		return SUCCESS;
	}

	public String getUserInfo() {
		User userInfo = (User) request.getSession().getAttribute("user");
		renderJSON(userInfo);
		return SUCCESS;
	}

	public String getUserById() {
		User user = userManager.getUserById(actionForm.getId());
		renderJSON(user);
		return SUCCESS;
	}
}
