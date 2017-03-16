package com.ctis.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ctis.util.LoginUtil;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
	public void attributeAdded(HttpSessionBindingEvent event) {
		LoginUtil.setSession(event.getSession());
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		LoginUtil.setSession(event.getSession());
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		LoginUtil.setSession(event.getSession());
	}
}
