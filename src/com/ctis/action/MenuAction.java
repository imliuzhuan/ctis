package com.ctis.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Function;
import com.ctis.entity.Role;
import com.ctis.entity.User;
import com.ctis.service.IFunctionManager;
import com.ctis.service.IRoleManager;
import com.ctis.util.LoginUtil;
import com.ctis.util.StringUtil;

@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Object> {
	private static final long serialVersionUID = 1L;
	@Resource
	private IRoleManager roleManager;
	@Resource
	private IFunctionManager functionManager;

	public String getMenu() {
		User user = (User) request.getSession().getAttribute("user");
		String[] roleIds = null;
		List<Role> roles = null;
		List<Function> functions = null;
		Set<String> funcIds = new HashSet<String>();
		if (!StringUtil.isNullStr(user.getRoleIds())) {
			roleIds = user.getRoleIds().split(",");
		}
		if (roleIds != null && roleIds.length > 0) {
			roles = roleManager.getRoles(roleIds);
		}
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				String[] funIds = role.getFuncIds().split(",");
				for (int i = 0; i < funIds.length; i++) {
					funcIds.add(funIds[i]);
				}
			}
		}
		if (funcIds != null && funcIds.size() > 0) {
			functions = functionManager.getFunctions(funcIds);
		}

		//构造菜单JSON字符串
		StringBuffer menus = new StringBuffer();
		menus.append("{\"menus\":[");
		int count = 0;
		String fGroup = null;
		for (Function function : functions) {
			String fg = function.getFuncGroup();
			if (fg.equals(fGroup)) {//同一组菜单
				menus.append(",{\"menuname\":\"").append(function.getName()).append("\",\"icon\":\"")
						.append(function.getIcon16()).append("\",\"url\":\"").append(function.getUrl()).append("\"}");
			} else if (fGroup != null && !fg.equals(fGroup)) {//不同组菜单
				count += 1;
				menus.append("]},{\"menuid\":\"").append(count).append("\",\"icon\":\"icon-sys\",\"menuname\":\"")
						.append(function.getFuncGroup()).append("\",\"menus\":[{\"menuname\":\"")
						.append(function.getName()).append("\",\"icon\":\"").append(function.getIcon16())
						.append("\",\"url\":\"").append(function.getUrl()).append("\"}");
			} else if (fGroup == null) {
				count = 1;
				menus.append("{\"menuid\":\"").append(count).append("\",\"icon\":\"icon-sys\",\"menuname\":\"")
						.append(function.getFuncGroup()).append("\",\"menus\":[{\"menuname\":\"")
						.append(function.getName()).append("\",\"icon\":\"").append(function.getIcon16())
						.append("\",\"url\":\"").append(function.getUrl()).append("\"}");
			}
			fGroup = fg;
		}
		if (count > 0) {
			menus.append("]}");
		}
		menus.append("]}");
		HttpSession session = request.getSession();
		LoginUtil.setSession(session);
		System.out.println("menus==>" + menus.toString());
		renderJSON(menus.toString());
		return SUCCESS;
	}
}
