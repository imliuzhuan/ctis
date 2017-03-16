package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 * @author liqq
 *
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 47L;
	@Id
	private String id;
	/**
	 * 用户名
	 */
	@Column(name = "USER_NAME")
	private String userName;
	/**
	 * 登录名
	 */
	@Column(name = "NICK_NAME", nullable = false, unique = true)
	private String nickName;
	/**
	 * 密码
	 */
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	/**
	 * 性别 0-女 1-男
	 */
	@Column(name = "GENDER")
	private String gender;

	/**
	 * 电话
	 */
	@Column(name = "TELEPHONE")
	private String telephone;
	/**
	 * 电子邮件
	 */
	@Column(name = "EMAIL")
	private String email;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**
	 * 是否系统管理员，一般默认只有admin一个系统管理员 0-否 1-是
	 */
	@Column(name = "IS_SYS")
	private String isSys;
	/**
	 * 存储角色ID,以","号隔开
	 */
	@Column(name = "ROLE_IDS", nullable = false)
	private String roleIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}
