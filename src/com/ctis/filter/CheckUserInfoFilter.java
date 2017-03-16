package com.ctis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctis.util.LoginUtil;

public class CheckUserInfoFilter implements Filter {
	private Log logger = LogFactory.getLog(CheckUserInfoFilter.class);

	public void destroy() {
		logger.info("##### filter.CheckUserInfoFilter.destroy() #####");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("##### filter.CheckUserInfoFilter.doFilter() #####");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String servletPath = request.getServletPath().toString().toLowerCase();
		LoginUtil.setSession(request.getSession());
		try {
			if (servletPath != null && servletPath.equalsIgnoreCase("/login.html")) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
			HttpSession session = request.getSession(false);
			if (servletPath.indexOf("login") == -1 && servletPath.indexOf("register") == -1
					&& (session == null || session.getAttribute("user") == null)) {
				throw new SecurityException("未找到session，请重新登录！！！");
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("##### filter.CheckUserInfoFilter.init() #####");
	}

}
