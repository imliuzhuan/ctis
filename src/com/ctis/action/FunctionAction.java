package com.ctis.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Function;
import com.ctis.service.IFunctionManager;
import com.ctis.util.PageResult;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	private static final long serialVersionUID = 47L;
	@Resource
	private IFunctionManager functionManager;
	Function function = actionForm;

	public String list() {
		PageResult pageResult = functionManager.list(function, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String add() {
		functionManager.add(function);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		functionManager.delete(function.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String modify() {
		functionManager.modify(function);
		renderJSON("ok");
		return SUCCESS;
	}
}
