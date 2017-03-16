package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统功能表
 * @author liqq
 *
 */
@Entity
@Table(name = "FUNCTION")
public class Function implements Serializable {
	private static final long serialVersionUID = 47L;
	/**
	 * 功能Key
	 */
	@Id
	private String id;
	/**
	 * 功能名称
	 */
	@Column(name = "NAME", nullable = false)
	private String name;
	/**
	 * 功能名称排序
	 */
	@Column(name = "FUN_ORDER")
	private Integer funOrder;
	/**
	 * 功能分组，指一级菜单，一级菜单不允许打开tab页
	 */
	@Column(name = "FUN_GROUP", nullable = false)
	private String funcGroup;

	/**
	 * 模块16x16图标url，用于菜单栏显示图标
	 */
	@Column(name = "ICON16")
	private String icon16;

	/**
	 * 功能页面地址
	 */
	@Column(name = "URL")
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFunOrder() {
		return funOrder;
	}

	public void setFunOrder(Integer funOrder) {
		this.funOrder = funOrder;
	}

	public String getFuncGroup() {
		return funcGroup;
	}

	public void setFuncGroup(String funcGroup) {
		this.funcGroup = funcGroup;
	}

	public String getIcon16() {
		return icon16;
	}

	public void setIcon16(String icon16) {
		this.icon16 = icon16;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
