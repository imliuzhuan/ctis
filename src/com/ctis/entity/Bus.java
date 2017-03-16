package com.ctis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 公交信息表
 * @author liqq
 *
 */
@Entity
@Table(name = "BUS")
public class Bus implements Serializable {
	private static final long serialVersionUID = 47L;
	@Id
	private String id;
	/**
	 * 线路编号
	 */
	@Column(name = "BUS_CODE")
	private String busCode;
	/**
	 * 首站(首站末站只是为了区别运行方向)
	 */
	@Column(name = "FIRST_STATION")
	private String firstStation;
	/**
	 * 首站运行起始时间
	 */
	@Column(name = "FIRST_START_TIME")
	private String firstStartTime;
	/**
	 * 首站运行截止时间
	 */
	@Column(name = "FIRST_END_TIME")
	private String firstEndTime;
	/**
	 * 末站
	 */
	@Column(name = "LAST_STATION")
	private String lastStation;
	/**
	 * 末站运行起始时间
	 */
	@Column(name = "LAST_START_TIME")
	private String lastStartTime;
	/**
	 * 末站运行截止时间
	 */
	@Column(name = "LAST_END_TIME")
	private String lastEndTime;
	/**
	 * 全程票价
	 */
	@Column(name = "PRICE")
	private String price;
	/**
	 * 上行各站站名
	 */
	@Column(name = "UP_STATIONS", length = 2000)
	private String upStations;
	/**
	 * 下行各站站名
	 */
	@Column(name = "DOWN_STATIONS", length = 2000)
	private String downStations;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**
	 * 创建者
	 */
	@Column(name = "CREATOR")
	private String creator;
	/**
	 * 修改时间
	 */
	@Column(name = "MODIFY_TIME")
	private String modifyTime;
	/**
	 * 修改者
	 */
	@Column(name = "MODIFIER")
	private String modifier;
	/**
	 * 公交车状态 0-暂停 1-运行
	 */
	@Column(name = "STATUS", length = 2)
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getFirstStation() {
		return firstStation;
	}

	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}

	public String getFirstStartTime() {
		return firstStartTime;
	}

	public void setFirstStartTime(String firstStartTime) {
		this.firstStartTime = firstStartTime;
	}

	public String getFirstEndTime() {
		return firstEndTime;
	}

	public void setFirstEndTime(String firstEndTime) {
		this.firstEndTime = firstEndTime;
	}

	public String getLastStation() {
		return lastStation;
	}

	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}

	public String getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(String lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	public String getLastEndTime() {
		return lastEndTime;
	}

	public void setLastEndTime(String lastEndTime) {
		this.lastEndTime = lastEndTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUpStations() {
		return upStations;
	}

	public void setUpStations(String upStations) {
		this.upStations = upStations;
	}

	public String getDownStations() {
		return downStations;
	}

	public void setDownStations(String downStations) {
		this.downStations = downStations;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取首站末站，用于页面显示
	 * @return
	 */
	public String getFirstAndEnd() {
		return this.firstStation + "-" + this.lastStation;
	}

	/**
	 * 获取工作时间，用于页面显示
	 * @return
	 */
	public String getWorkTime() {
		String upTime = this.firstStation + " " + this.firstStartTime + "-" + this.firstEndTime;
		String downTime = this.lastStation + " " + this.lastStartTime + "-" + this.lastEndTime;
		if (upTime.equals(downTime)) {
			return "(" + upTime + ")";
		}
		return "(" + upTime + "|" + downTime + ")";
	}

	/**
	 * 获取中文状态，用于页面显示
	 * @return
	 */
	public String getShowStatus() {
		if (this.status.equals("0")) {
			return "暂停";
		}
		if (this.status.equals("1")) {
			return "运行";
		}
		return "";
	}
}
