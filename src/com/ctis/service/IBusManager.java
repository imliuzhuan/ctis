package com.ctis.service;

import java.util.List;

import com.ctis.entity.Bus;
import com.ctis.entity.User;
import com.ctis.util.PageResult;

public interface IBusManager {
	/**
	 * 查询公交车列表
	 * @param bus
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult list(Bus bus, int page, int rows, String sort, String order);

	/**
	 * 增加一条公交车记录
	 * @param bus
	 * @return
	 */
	void add(Bus bus);

	/**
	 * 修改公交车信息
	 * @param bus
	 * @return
	 */
	void modify(Bus bus);

	/**
	 * 删除一条公交车数据
	 * @param id
	 * @return
	 */
	void delete(String id);

	/**
	 * 删除多条公交车数据
	 * @param ids
	 */
	void deleteMore(String[] ids);

	/**
	 * 删除所有公交车数据
	 */
	void deleteAll();

	/**
	 * 查询一条公交车数据
	 * @param id
	 * @return
	 */
	Bus view(String id);

	/**
	 * 初始化公交车数据
	 * @param user
	 * @return
	 */
	boolean init(User user);

	/**
	 * 改变公交车状态
	 * @param id
	 */
	void changeStatus(String id);

	/**
	 * 根据ID获取公交车信息
	 * @param id
	 * @return
	 */
	Bus getBusInfo(String id);

	/**
	 * 获取公交车列表数据
	 * @return
	 */
	List<Bus> getBusList();

	/**
	 * 根据站点模糊查询公交信息
	 * @param station
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	PageResult listByStation(String station, int page, int rows, String sort, String order);

	/**
	 * 公交换乘查询
	 * @param from
	 * @param to
	 * @param page
	 * @param rows
	 * @return
	 */
	PageResult getNeedLines(String from, String to, int page, int rows);

	/**
	 * 获取所有公交站点
	 * @return
	 */
	List<String> getStations();

	/**
	 * 获取所有公交线路名称
	 * @return
	 */
	List<String> getBusCodes();
}
