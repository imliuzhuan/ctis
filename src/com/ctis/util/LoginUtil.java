package com.ctis.util;

import javax.servlet.http.HttpSession;

import com.ctis.entity.User;

public class LoginUtil {
	private static final ThreadLocal<HttpSession> localSession = new ThreadLocal<HttpSession>();

	public static void setSession(HttpSession session) {
		localSession.set(session);
	}

	public static HttpSession getSession() {
		return (HttpSession) localSession.get();
	}

	/**
	 * 清空ThreadLocal里的值
	 *
	 */
	public static void clearThreadLocal() {
		localSession.set(null);
	}

	public static String getUserId() {
		User user = getUser();
		if (user != null) {
			return user.getId();
		}
		return "";
	}

	public static String getUserName() {
		User user = getUser();
		if (user != null) {
			return user.getUserName();
		}
		return "";
	}

	public static String getNickName() {
		User user = getUser();
		if (user != null) {
			return user.getNickName();
		}
		return "";
	}

	public static User getUser() {
		HttpSession session = getSession();
		User user = null;
		try {
			user = (User) session.getAttribute("user");
		} catch (Exception e) {
		}
		return user;
	}
}
