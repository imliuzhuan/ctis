package com.ctis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.IRoleDao;
import com.ctis.entity.Role;
import com.ctis.service.IRoleManager;
import com.ctis.util.Constant;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Service
@Transactional
public class RoleManagerImpl implements IRoleManager {
	@Resource
	private IRoleDao roleDao;

	public List<Role> getRoles(String[] roleIds) {
		return roleDao.getRoles(roleIds);
	}

	public PageResult list(Role role, int page, int rows, String sort, String order) {
		PageResult result = roleDao.list(role, page, rows, sort, order);
		return result;
	}

	public void add(Role role) {
		role.setId(Constant.getSeries());
		roleDao.add(role);
	}

	public void delete(String id) {
		if (!StringUtil.isNullStr(id)) {
			roleDao.delete(id);
		}
	}

	public void modify(Role role) {
		if (role != null && !StringUtil.isNullStr(role.getId())) {
			roleDao.modify(role);
		}
	}

	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
}
