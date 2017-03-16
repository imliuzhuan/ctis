package com.ctis.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.IFunctionDao;
import com.ctis.entity.Function;
import com.ctis.entity.Role;
import com.ctis.service.IFunctionManager;
import com.ctis.util.Constant;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Service
@Transactional
public class FunctionManagerImpl implements IFunctionManager {
	@Resource
	private IFunctionDao functionDao;

	public List<Function> getFunctions(Set<String> funcIds) {
		return functionDao.getFunctions(funcIds);
	}

	public PageResult list(Function function, int page, int rows, String sort, String order) {
		PageResult pageResult = functionDao.list(function, page, rows, sort, order);
		return pageResult;
	}

	public void add(Function function) {
		function.setId(Constant.getSeries());
		functionDao.add(function);
	}

	public void delete(String id) {
		if (!StringUtil.isNullStr(id)) {
			functionDao.delete(id);
		}
	}

	public void modify(Function function) {
		if (function != null && !StringUtil.isNullStr(function.getId())) {
			functionDao.modify(function);
		}
	}

	public PageResult getFuncs(String[] funcIds, int page, int rows) {
		PageResult pageResult = functionDao.getFuncs(funcIds, page, rows);
		return pageResult;
	}

	public PageResult getOtherFuncs(Role role, int page, int rows) {
		PageResult pageResult = functionDao.getOtherFuncs(role, page, rows);
		return pageResult;
	}
}
