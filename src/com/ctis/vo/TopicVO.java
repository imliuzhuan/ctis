package com.ctis.vo;

import java.util.List;

import com.ctis.entity.Comments;
import com.ctis.entity.Topic;

public class TopicVO {
	private Topic topic;
	private List<Comments> comments;

	public TopicVO() {
		super();
	}

	public TopicVO(Topic topic, List<Comments> comments) {
		super();
		this.topic = topic;
		this.comments = comments;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
}
