package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评论表
 * @author liqq
 *
 */
@Entity
@Table(name = "COMMENTS")
public class Comments implements Serializable {
	private static final long serialVersionUID = 47L;
	/**
	 * 评论ID
	 */
	@Id
	private String id;
	/**
	 * 话题ID
	 */
	@Column(name = "TOPIC_ID", nullable = false)
	private String topicId;
	/**
	 * 评论内容
	 */
	@Column(name = "CONTENT", nullable = false)
	private String content;
	/**
	 * 评论状态 0-待审核 1-审核未通过 2-审核通过
	 */
	@Column(name = "STATE", nullable = false)
	private String state;
	/**
	 * 创建者
	 */
	@Column(name = "CREATOR")
	private String creator;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**
	 * 用户点赞的数量，点赞数量最多的前三名将排在最前面
	 */
	@Column(name = "PRAISE")
	private Integer praise;
	/**
	 * 用户点踩的数量
	 */
	@Column(name = "TREAD")
	private Integer tread;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getTread() {
		return tread;
	}

	public void setTread(Integer tread) {
		this.tread = tread;
	}
}
