package com.ctis.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Role;
import com.ctis.service.IFunctionManager;
import com.ctis.service.IRoleManager;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 47L;
	@Resource
	private IRoleManager roleManager;
	@Resource
	private IFunctionManager functionManager;
	Role role = actionForm;
	public String delFuncIds;

	public String list() {
		PageResult pageResult = roleManager.list(role, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String add() {
		roleManager.add(role);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		roleManager.delete(role.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String modify() {
		roleManager.modify(role);
		renderJSON("ok");
		return SUCCESS;
	}

	public String getFuncs() {
		PageResult pageResult = null;
		String[] funcIds = StringUtil.isNullStr(role.getFuncIds()) ? null : role.getFuncIds().split(",");
		if (funcIds != null && funcIds.length > 0) {
			pageResult = functionManager.getFuncs(funcIds, page, rows);
		} else {
			pageResult = new PageResult();
		}
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String getOtherFuncs() {
		PageResult pageResult = functionManager.getOtherFuncs(role, page, rows);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String delFuncs() {
		String[] delFunIds = StringUtil.isNullStr(delFuncIds) ? new String[] {} : delFuncIds.split(",");
		String[] roleFuncIds = StringUtil.isNullStr(role.getFuncIds()) ? new String[] {} : role.getFuncIds().split(",");
		for (int i = 0; i < delFunIds.length; i++) {
			for (int j = 0; j < roleFuncIds.length; j++) {
				if (delFunIds[i].equals(roleFuncIds[j])) {
					roleFuncIds[j] = null;
				}
			}
		}
		String newFuncIds = "";
		for (String roleFuncId : roleFuncIds) {
			if (!StringUtil.isNullStr(roleFuncId)) {
				newFuncIds += roleFuncId + ",";
			}
		}
		if (newFuncIds.length() > 0) {
			newFuncIds = newFuncIds.substring(0, newFuncIds.length() - 1);
		}
		role.setFuncIds(newFuncIds);
		roleManager.modify(role);
		Map<String, String> map = new HashMap<String, String>();
		map.put("newFuncIds", newFuncIds);
		renderJSON(map);
		return SUCCESS;
	}

	public String getRoles() {
		List<Role> roles = roleManager.getRoles();
		renderJSON(roles);
		return SUCCESS;
	}
}
