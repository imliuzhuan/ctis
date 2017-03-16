package com.ctis.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctis.entity.Bus;
import com.ctis.entity.User;
import com.ctis.service.IBusManager;
import com.ctis.util.DiscernUtil;
import com.ctis.util.PageResult;
import com.ctis.util.PinYinUtil;

@Controller
@Scope("prototype")
public class BusAction extends BaseAction<Bus> {
	private static final long serialVersionUID = 47L;
	@Resource
	private IBusManager busManager;
	private Bus bus = actionForm;
	public String[] ids;
	public String station;
	public String from;
	public String to;

	public String list() {
		PageResult pageResult = busManager.list(bus, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String add() {
		busManager.add(bus);
		renderJSON("ok");
		return SUCCESS;
	}

	public String delete() {
		busManager.delete(bus.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String modify() {
		busManager.modify(bus);
		renderJSON("ok");
		return SUCCESS;
	}

	public String deleteMore() {
		busManager.deleteMore(ids);
		renderJSON("ok");
		return SUCCESS;
	}

	public String view() {
		busManager.view(bus.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String changeStatus() {
		busManager.changeStatus(bus.getId());
		renderJSON("ok");
		return SUCCESS;
	}

	public String init() {
		User user = (User) request.getSession().getAttribute("user");
		busManager.init(user);
		renderJSON("ok");
		return SUCCESS;
	}

	public String getBusListArea() {
		String _buses = "";
		List<Bus> busList = busManager.getBusList();
		if (busList != null && busList.size() > 0) {
			Map<Map<String, String>, String> numMap = new HashMap<Map<String, String>, String>();
			Map<Map<String, String>, String> chineseMap = new HashMap<Map<String, String>, String>();
			Set<String> numSet = new HashSet<String>();
			Set<String> chineseSet = new HashSet<String>();
			for (Bus bus : busList) {
				Map<String, String> tmpMap = new HashMap<String, String>();
				String s = PinYinUtil.getFirstSpell(bus.getBusCode()).substring(0, 1).toUpperCase();
				tmpMap.put(bus.getId(), bus.getBusCode());
				if (DiscernUtil.isNumeric(s)) {
					numSet.add(s);
					numMap.put(tmpMap, s);
				}
				if (DiscernUtil.isLetter(s)) {
					chineseSet.add(s);
					chineseMap.put(tmpMap, s);
				}
			}
			String[] numArr = numSet.toArray(new String[numSet.size()]);
			Arrays.sort(numArr);
			String[] chineseArr = chineseSet.toArray(new String[chineseSet.size()]);
			Arrays.sort(chineseArr);
			//构建json字符串
			_buses += "{\"num\":[";
			String listStr = "";
			for (String num : numArr) {
				_buses += "\"" + num + "\",";
				listStr += "{\"anchor\":\"" + num + "\",\"buses\":[";
				for (Entry<Map<String, String>, String> entryMap : numMap.entrySet()) {
					if (num.equals(entryMap.getValue())) {
						for (Entry<String, String> entry : entryMap.getKey().entrySet()) {
							listStr += "{\"id\":\"" + entry.getKey() + "\",\"busCode\":\"" + entry.getValue() + "\"},";
						}
					}
				}
				if (listStr.charAt(listStr.length() - 1) == ',') {
					listStr = listStr.substring(0, listStr.length() - 1);
				}
				listStr += "]},";
			}
			if (numArr.length > 0) {
				_buses = _buses.substring(0, _buses.length() - 1);
			}
			_buses += "],\"chinese\":[";
			for (String chinese : chineseArr) {
				_buses += "\"" + chinese + "\",";
				listStr += "{\"anchor\":\"" + chinese + "\",\"buses\":[";
				for (Entry<Map<String, String>, String> entryMap : chineseMap.entrySet()) {
					if (chinese.equals(entryMap.getValue())) {
						for (Entry<String, String> entry : entryMap.getKey().entrySet()) {
							listStr += "{\"id\":\"" + entry.getKey() + "\",\"busCode\":\"" + entry.getValue() + "\"},";
						}
					}
				}
				if (listStr.charAt(listStr.length() - 1) == ',') {
					listStr = listStr.substring(0, listStr.length() - 1);
				}
				listStr += "]},";
			}
			if (chineseArr.length > 0) {
				_buses = _buses.substring(0, _buses.length() - 1);
			}
			if (listStr.charAt(listStr.length() - 1) == ',') {
				listStr = listStr.substring(0, listStr.length() - 1);
			}
			_buses += "],\"list\":[" + listStr + "]}";
		}
		System.out.println("_buses ==> " + _buses);
		renderJSON(_buses);
		return SUCCESS;
	}

	public String getBusInfo() {
		Bus bus = busManager.getBusInfo(actionForm.getId());
		renderJSON(bus);
		return SUCCESS;
	}

	public String listByStation() {
		PageResult pageResult = busManager.listByStation(station, page, rows, sort, order);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String getNeedLines() {
		PageResult pageResult = busManager.getNeedLines(from, to, page, rows);
		renderJSON(pageResult);
		return SUCCESS;
	}

	public String getStations() {
		List<String> list = busManager.getStations();
		renderJSON(list);
		return SUCCESS;
	}

	public String getBusCodes() {
		List<String> list = busManager.getBusCodes();
		renderJSON(list);
		return SUCCESS;
	}
}
