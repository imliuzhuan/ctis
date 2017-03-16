package com.ctis.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.User;
import com.ctis.service.ILoginManager;
import com.ctis.service.IUserManager;
import com.ctis.util.ImageUtil;
import com.ctis.util.LoginUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> {
	private static final long serialVersionUID = 47L;
	@Resource
	private ILoginManager loginManager;
	@Resource
	private IUserManager userManager;
	//生成验证码，输送到前台
	public InputStream vercodeStream;
	//接受用户输入的验证码
	public String vercode;
	public String oldPass;

	public String login() {
		//获取用户输入的昵称和验证码
		User user = actionForm;
		//获取HttpSession中的vercode属性
		String ver2 = (String) request.getSession().getAttribute("vercode");
		if (vercode.equalsIgnoreCase(ver2)) {
			//调用业务逻辑方法来处理登录请求
			User result = loginManager.validLogin(user);
			//登录成功
			if (result != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", result);
				LoginUtil.setSession(session);
				renderJSON("ok");
			} else {//用户名和密码不匹配
				renderJSON("up");
			}
		} else {//验证码不匹配
			renderJSON("vc");
		}
		return SUCCESS;
	}

	public String logout() {
		HttpSession session = request.getSession();
		session.invalidate();
		LoginUtil.clearThreadLocal();
		renderJSON("ok");
		return SUCCESS;
	}

	public String register() {
		User user = actionForm;
		userManager.add(user);
		renderJSON("ok");
		return SUCCESS;
	}

	public String isPassRight() {
		User user = (User) request.getSession().getAttribute("user");
		String password = user.getPassword();
		if (oldPass.equals(password)) {
			renderJSON("ok");
		}
		return SUCCESS;
	}

	public String modifyPassword() {
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(actionForm.getPassword());
		User user2 = userManager.modify(user);
		request.setAttribute("user", user2);
		LoginUtil.setSession(request.getSession());
		renderJSON("ok");
		return SUCCESS;
	}

	public String modifyUserInfo() {
		User user = actionForm;
		User user2 = userManager.modify(user);
		request.setAttribute("user", user2);
		LoginUtil.setSession(request.getSession());
		renderJSON(user2);
		return SUCCESS;
	}

	public String vercode() {
		// 1.调用工具类，生成验证码及图片
		Map<String, BufferedImage> vercodeMap = ImageUtil.createImage();
		// 2.从imageMap中取到验证码，并放入session
		String vercode = vercodeMap.keySet().iterator().next();
		HttpSession session = request.getSession();
		session.setAttribute("vercode", vercode);
		LoginUtil.setSession(session);
		// 3.从imageMap中取到图片，转为输入流
		BufferedImage vercodeImage = vercodeMap.get(vercode);
		try {
			vercodeStream = ImageUtil.getInputStream(vercodeImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "vercode";
	}
}
