package com.ctis.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ctis.dao.IBusDao;
import com.ctis.entity.Bus;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;

@Repository
public class BusDaoImpl extends HibernateBaseDaoImpl implements IBusDao {
	public PageResult list(Bus bus, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Bus b where 1=1 ");
		if (bus != null && !StringUtil.isNullStr(bus.getBusCode())) {
			hql.append(" and b.busCode like '%").append(bus.getBusCode()).append("%' ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by b.busCode ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public void add(Bus bus) {
		this.create(bus);
	}

	public void modify(Bus bus) {
		this.update(bus);
	}

	public void delete(String id) {
		this.delete(Bus.class, id);
	}

	public void deleteMore(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			this.delete(ids[i]);
		}
	}

	public void deleteAll() {
		this.deleteAll(Bus.class);
	}

	public Bus view(String id) {
		Bus bus = (Bus) this.get(Bus.class, id);
		return bus;
	}

	public boolean init(List<Bus> buses) {
		if (buses != null && buses.size() > 0) {
			deleteAll();
			for (Bus bus : buses) {
				add(bus);
			}
			return true;
		}
		return false;
	}

	public void changeStatus(String id) {
		Bus bus = (Bus) get(Bus.class, id);
		if (bus != null) {
			if (bus.getStatus().equals("1")) {
				bus.setStatus("0");
			} else {
				bus.setStatus("1");
			}
			modify(bus);
		}
	}

	public Bus getBusInfo(String id) {
		Bus bus = (Bus) get(Bus.class, id);
		return bus;
	}

	@SuppressWarnings("unchecked")
	public List<Bus> getBusList() {
		Query query = currentSession().createQuery("from Bus b order by b.busCode");
		return query.list();
	}

	public PageResult listByStation(String station, int page, int rows, String sort, String order) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Bus b where 1=1 ");
		if (!StringUtil.isNullStr(station)) {
			hql.append(" and (b.upStations like '%").append(station).append("%' ").append(" or b.downStations like '%")
					.append(station).append("%') ");
		}
		if (StringUtil.isNullStr(sort)) {
			hql.append(" order by b.busCode ");
		} else {
			hql.append(" order by ").append(sort).append(" ").append(order);
		}
		return this.findPageResult(hql.toString(), page, rows);
	}

	public List<String> getStations() {
		Set<String> set = getStations(getBusList());
		List<String> rtn = new ArrayList<String>();
		rtn.addAll(set);
		Collections.sort(rtn);
		return rtn;
	}

	/**
	 * 获取所有公交站点
	 * @param list
	 * @return
	 */
	private Set<String> getStations(List<Bus> list) {
		Set<String> set = new HashSet<String>();
		for (Bus bus : list) {
			set.addAll(Arrays.asList(bus.getUpStations().split("-")));
			set.addAll(Arrays.asList(bus.getDownStations().split("-")));
		}
		return set;
	}

	public List<String> getBusCodes() {
		List<Bus> busList = getBusList();
		List<String> rtn = new ArrayList<String>();
		for (Bus bus : busList) {
			rtn.add(bus.getBusCode());
		}
		Collections.sort(rtn);
		return rtn;
	}
}
