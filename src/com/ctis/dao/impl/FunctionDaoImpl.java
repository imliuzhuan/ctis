package com.ctis.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.IFunctionDao;
import com.ctis.entity.Function;
import com.ctis.entity.Role;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class FunctionDaoImpl extends HibernateBaseDaoImpl implements IFunctionDao {
	@SuppressWarnings("unchecked")
	public List<Function> getFunctions(Set<String> funcIds) {
		Query query = currentSession().createQuery(
				"from Function t where t.id in(:funcIds) order by t.funcGroup,t.funOrder ");
		query.setParameterList("funcIds", funcIds);
		return query.list();
	}

	public PageResult list(Function function, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Function f where 1=1 ");
		if (function != null && !StringUtil.isNullStr(function.getName())) {
			hql.append(" and f.name like '%").append(function.getName()).append("%' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by f.funOrder ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public void add(Function function) {
		this.create(function);
	}

	public void delete(String id) {
		this.delete(Function.class, id);
	}

	public void modify(Function function) {
		this.update(function);
	}

	@SuppressWarnings("unchecked")
	public PageResult getFuncs(String[] funcIds, int page, int rows) {
		Query query = currentSession().createQuery(
				"from Function t where t.id in(:funcIds) order by t.funcGroup,t.funOrder ");
		query.setParameterList("funcIds", funcIds);
		PageResult pageResult = new PageResult(query.list(), page, rows);
		return pageResult;
	}

	@SuppressWarnings("unchecked")
	public PageResult getOtherFuncs(Role role, int page, int rows) {
		String[] funcIds = StringUtil.isNullStr(role.getFuncIds()) ? new String[] { "__" } : role.getFuncIds().split(",");
		StringBuffer sb = new StringBuffer();
		sb.append("from Function t where t.id not in(:funcIds) ");
		if (!StringUtil.isNullStr(role.getName())) {
			sb.append("and t.name like '%").append(role.getName()).append("%' ");
		}
		sb.append("order by t.funcGroup,t.funOrder ");
		Query query = currentSession().createQuery(sb.toString());
		query.setParameterList("funcIds", funcIds);
		PageResult pageResult = new PageResult(query.list(), page, rows);
		return pageResult;
	}
}
