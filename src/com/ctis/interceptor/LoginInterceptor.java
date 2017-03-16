package com.ctis.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.ctis.entity.User;
import com.ctis.util.LoginUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登陆检查拦截器
 * @author liqq
 *
 */
@SuppressWarnings("serial")
public class LoginInterceptor implements Interceptor {
	private Log logger = LogFactory.getLog(LoginInterceptor.class);

	@Override
	public void destroy() {
		logger.info("##### interceptor.LoginInterceptor.destroy() #####");
	}

	@Override
	public void init() {
		logger.info("##### interceptor.LoginInterceptor.init() #####");
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		logger.info("##### interceptor.LoginInterceptor.intercept() #####");
		HttpSession session = ServletActionContext.getRequest().getSession();
		LoginUtil.setSession(session);
		User user = (User) session.getAttribute("user");
		if (null != user) {
			//不为空，说明登陆了，可以访问
			return ai.invoke();
		} else {
			//为空说明没登陆
			throw new SecurityException("未找到session，请重新登录！！！");
		}
	}
}
