package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 * @author liqq
 *
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 47L;
	/**
	 * 角色id
	 */
	@Id
	private String id;
	/**
	 * 角色名字
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 功能key,存储系统功能表主键,以","隔开
	 */
	@Column(name = "FUNC_ID")
	private String funcIds;

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

	public String getFuncIds() {
		return funcIds;
	}

	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}
}
