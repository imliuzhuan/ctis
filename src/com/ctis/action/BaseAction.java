package com.ctis.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ServletRequestAware, ApplicationContextAware,
		ModelDriven<T> {
	private static final long serialVersionUID = 47L;
	public int page;
	public int rows;
	public String sort;
	public String order;
	public Object data;
	/**
	 * 声明的参数类
	 */
	protected T actionForm;
	/**
	 * request对象
	 */
	protected HttpServletRequest request;
	/**
	 * spring上下文，用于动态获取bean对象
	 */
	protected ApplicationContext application;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setApplicationContext(ApplicationContext application) throws BeansException {
		this.application = application;
	}

	/**
	 * 构造参数对象
	 */
	public BaseAction() {
		if (actionForm == null) {
			try {
				actionForm = getParameterizedTypeClass().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> getParameterizedTypeClass() {
		// 获取泛型类型
		Type type = getClass().getGenericSuperclass();
		Type[] trueType = ((ParameterizedType) type).getActualTypeArguments();
		return (Class<T>) trueType[0];
	}

	public T getActionForm() {
		return actionForm;
	}

	public void setActionForm(T actionForm) {
		this.actionForm = actionForm;
	}

	/**
	 * 将泛型参数类映射为Struts的默认参数包装对象
	 */
	public T getModel() {
		return actionForm;
	}

	/**
	 * 向前端返回json数据
	 * @param data
	 */
	protected void renderJSON(Object data) {
		this.data = data;
	}
}
