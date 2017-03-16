package com.ctis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.IRoleDao;
import com.ctis.entity.Role;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class RoleDaoImpl extends HibernateBaseDaoImpl implements IRoleDao {
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(String[] roleIds) {
		Query query = currentSession().createQuery("from Role t where t.id in (:ids)");
		query.setParameterList("ids", roleIds);
		return query.list();
	}

	public PageResult list(Role role, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Role r where 1=1 ");
		if (role != null && !StringUtil.isNullStr(role.getName())) {
			hql.append(" and r.name = '").append(role.getName()).append("' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by r.id ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public void add(Role role) {
		this.create(role);
	}

	public void delete(String id) {
		this.delete(Role.class, id);
	}

	public void modify(Role role) {
		this.update(role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		List<Role> roles = this.query("from Role r ");
		return roles;
	}
}
