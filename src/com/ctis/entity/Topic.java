package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 话题表
 * @author liqq
 *
 */
@Entity
@Table(name = "TOPIC")
public class Topic implements Serializable {
	private static final long serialVersionUID = 47L;
	/**
	 * 话题ID
	 */
	@Id
	private String id;
	/**
	 * 话题名称
	 */
	@Column(name = "NAME", nullable = false)
	private String name;
	/**
	 * 话题内容
	 */
	@Column(name = "CONTENT", nullable = false)
	private String content;
	/**
	 * 创建者
	 */
	@Column(name = "CREATOR")
	private String creator;
	/**
	 * 最后一次修改时间
	 */
	@Column(name = "LAST_MODIFY_TIME")
	private String lastModifyTime;
	/**
	 * 话题状态 0-未发布 1-已发布
	 */
	@Column(name = "STATE", nullable = false)
	private String state;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
