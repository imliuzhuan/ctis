package com.ctis.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Topic;
import com.ctis.entity.User;
import com.ctis.service.ITopicManager;
import com.ctis.util.Constant;
import com.ctis.util.DateUtil;
import com.ctis.util.PageResult;
import com.ctis.vo.TopicVO;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	private static final long serialVersionUID = 47L;
	@Resource
	private ITopicManager topicManager;
	public String dateFrom;
	public String dateTo;

	public String list() {
		PageResult pageResult = topicManager.list(actionForm, dateFrom, dateTo, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String todayList() {
		actionForm.setLastModifyTime(DateUtil.getCurrentDateString());
		actionForm.setState("1");
		PageResult pageResult = topicManager.list(actionForm, null, null, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String getTopic() {
		Topic topic = topicManager.getTopic(actionForm.getId());
		renderJSON(topic);
		return SUCCESS;
	}

	public String add() {
		User user = (User) request.getSession().getAttribute("user");
		actionForm.setId(Constant.getSeries());
		actionForm.setCreator(user.getId());
		actionForm.setLastModifyTime(DateUtil.getCurrentTimeString());
		actionForm.setState("0");
		topicManager.add(actionForm);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		topicManager.delete(actionForm.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String modify() {
		actionForm.setLastModifyTime(DateUtil.getCurrentTimeString());
		topicManager.modify(actionForm);
		renderJSON("ok");
		return SUCCESS;
	}

	public String getTopicVO() {
		TopicVO topiVo = topicManager.getTopicVO(actionForm.getId());
		renderJSON(topiVo);
		return SUCCESS;
	}
}
