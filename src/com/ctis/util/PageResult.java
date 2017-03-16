package com.ctis.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装查询结果集
 * @author liqq
 *
 */
public class PageResult implements Serializable {
	private static final long serialVersionUID = 47L;
	/**
	 * 结果集列数
	 */
	private List<Object> rows;
	/**
	 * 结果集总行数
	 */
	private int total = 0;

	public PageResult() {
		super();
		this.rows = new ArrayList<Object>();
		this.total = 0;
	}

	public PageResult(List<Object> rows) {
		super();
		this.rows = rows;
		if (rows != null) {
			this.total = rows.size();
		}
	}

	public PageResult(List<Object> rows, int total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public PageResult(List<Object> data, int page, int rows) {
		List<Object> pageData = new ArrayList<Object>();
		for (int i = rows * (page - 1); i < rows * page; i++) {
			if (i < data.size()) {
				Object rowData = data.get(i);
				pageData.add(rowData);
			} else {
				break;
			}
		}
		this.rows = pageData;
		this.total = data.size();
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}