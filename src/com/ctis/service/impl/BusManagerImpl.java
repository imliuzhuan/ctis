package com.ctis.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctis.dao.IBusDao;
import com.ctis.entity.Bus;
import com.ctis.entity.User;
import com.ctis.service.IBusManager;
import com.ctis.util.Constant;
import com.ctis.util.DateUtil;
import com.ctis.util.LoginUtil;
import com.ctis.util.PageResult;
import com.ctis.util.StringUtil;
import com.ctis.vo.Route;

@Service
@Transactional
public class BusManagerImpl implements IBusManager {
	@Resource
	private IBusDao busDao;

	public PageResult list(Bus bus, int page, int rows, String sort, String order) {
		PageResult result = busDao.list(bus, page, rows, sort, order);
		return result;
	}

	public void add(Bus bus) {
		if (bus != null) {
			bus.setId(Constant.getSeries());
			bus.setCreateTime(DateUtil.getCurrentTimeString());
			bus.setCreator(LoginUtil.getUserId());
			bus.setStatus("1");
			busDao.add(bus);
		}
	}

	public void modify(Bus bus) {
		if (bus != null && bus.getId() != null) {
			busDao.modify(bus);
		}
	}

	public void delete(String id) {
		if (id != null) {
			busDao.delete(id);
		}
	}

	public void deleteMore(String[] ids) {
		if (ids != null && ids.length > 0) {
			busDao.deleteMore(ids);
		}
	}

	public void deleteAll() {
		busDao.deleteAll();
	}

	public Bus view(String id) {
		Bus bus = null;
		if (id != null) {
			bus = busDao.view(id);
		}
		return bus;
	}

	public boolean init(User user) {
		boolean rtn = false;
		try {
			List<Bus> buses = readExcelFile(user);
			rtn = busDao.init(buses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	/**
	 * 将excel文件解析成javabean对象
	 * @param buses
	 */
	@SuppressWarnings("resource")
	private List<Bus> readExcelFile(User user) {
		List<Bus> buses = new ArrayList<Bus>();
		try {
			String path = Constant.getTmpFilePath();
			File readfile = new File(path);
			if (!readfile.isDirectory()) {
				Workbook workbook = null;
				//兼容excel各版本的写法
				try {
					workbook = new HSSFWorkbook(new FileInputStream(readfile));
				} catch (Exception e) {
					workbook = new XSSFWorkbook(new FileInputStream(readfile));
				}
				Sheet sheet = workbook.getSheetAt(0);
				if (sheet.getLastRowNum() > 0) {
					for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
						Bus bus = new Bus();
						Row row = sheet.getRow(rowNum);
						Cell cell1 = row.getCell(0);
						if (cell1 != null && cell1.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setBusCode(cell1.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						Cell cell2 = row.getCell(1);
						if (cell2 != null && cell2.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setFirstStation(cell2.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						Cell cell3 = row.getCell(2);
						if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setFirstStartTime(cell3.getStringCellValue());
						} else if (cell3 != null && cell3.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell3)) {
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								bus.setFirstStartTime(sdf.format(HSSFDateUtil.getJavaDate(cell3.getNumericCellValue()))
										.toString());
							} else {
								bus.setFirstStartTime(String.valueOf(cell3.getNumericCellValue()));
							}
						} else {
							buses.clear();
							break;
						}
						Cell cell4 = row.getCell(3);
						if (cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setFirstEndTime(cell4.getStringCellValue());
						} else if (cell4 != null && cell4.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell4)) {
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								bus.setFirstEndTime(sdf.format(HSSFDateUtil.getJavaDate(cell4.getNumericCellValue()))
										.toString());
							} else {
								bus.setFirstStartTime(String.valueOf(cell4.getNumericCellValue()));
							}
						} else {
							buses.clear();
							break;
						}
						Cell cell5 = row.getCell(4);
						if (cell5 != null && cell5.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setLastStation(cell5.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						Cell cell6 = row.getCell(5);
						if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setLastStartTime(cell6.getStringCellValue());
						} else if (cell6 != null && cell6.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell6)) {
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								bus.setLastStartTime(sdf.format(HSSFDateUtil.getJavaDate(cell6.getNumericCellValue()))
										.toString());
							} else {
								bus.setFirstStartTime(String.valueOf(cell6.getNumericCellValue()));
							}
						} else {
							buses.clear();
							break;
						}
						Cell cell7 = row.getCell(6);
						if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setLastEndTime(cell7.getStringCellValue());
						} else if (cell7 != null && cell7.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell7)) {
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								bus.setLastEndTime(sdf.format(HSSFDateUtil.getJavaDate(cell7.getNumericCellValue()))
										.toString());
							} else {
								bus.setFirstStartTime(String.valueOf(cell7.getNumericCellValue()));
							}
						} else {
							buses.clear();
							break;
						}
						Cell cell8 = row.getCell(7);
						if (cell8 != null && cell8.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setPrice(cell8.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						Cell cell9 = row.getCell(8);
						if (cell9 != null && cell9.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setUpStations(cell9.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						Cell cell10 = row.getCell(9);
						if (cell10 != null && cell10.getCellType() == Cell.CELL_TYPE_STRING) {
							bus.setDownStations(cell10.getStringCellValue());
						} else {
							buses.clear();
							break;
						}
						bus.setId(Constant.getSeries());
						bus.setCreateTime(DateUtil.getCurrentTimeString());
						bus.setCreator(user == null ? "" : user.getId());
						bus.setStatus("1");
						buses.add(bus);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buses;
	}

	public void changeStatus(String id) {
		if (id != null) {
			busDao.changeStatus(id);
		}
	}

	public Bus getBusInfo(String id) {
		if (!StringUtil.isNullStr(id)) {
			return busDao.getBusInfo(id);
		}
		return null;
	}

	public List<Bus> getBusList() {
		return busDao.getBusList();
	}

	public PageResult listByStation(String station, int page, int rows, String sort, String order) {
		PageResult pageResult = busDao.listByStation(station, page, rows, sort, order);
		return pageResult;
	}

	public PageResult getNeedLines(String from, String to, int page, int rows) {
		//获取所有公交线路
		List<Bus> all = getBusList();
		if (all == null || all.size() == 0)
			return null;
		//所有包含起点的公交线路
		List<Bus> fromBuses = new ArrayList<Bus>();
		for (Bus bus : all) {
		//获取当前线路所有站点
			Set<String> stations = getStations(bus);
			if (stations.contains(from)) {
				fromBuses.add(bus);
			}
		}
		if (fromBuses.size() == 0)
			return null;
		List<List<String[]>> result = new ArrayList<List<String[]>>();//3 id 13 路  线路 1 a b c  c d
		for (Bus bus : fromBuses) {
			List<Bus> past = new ArrayList<Bus>();
			past.add(bus);
			getRoutes(result, null, all, past, bus, from, to, 0);
		}
		if (result.size() == 0)
			return null;
		List<Object> routes = new ArrayList<Object>();
		String styleStart = " <span style='font-weight: bold;color: red;'>";
		String styleEnd = "</span> ";
		for (List<String[]> sites : result) {
			Route route = new Route();
			route.setTransTime(sites.size() - 2);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < sites.size(); i++) {
				String[] site = sites.get(i);
				String distance = "";
				if (i < sites.size() - 1) {
					distance = String.valueOf(getSiteDistance(getBusInfo(site[0]), site[2], sites.get(i + 1)[2]));
				}
				if (i == 0) {
					sb.append("乘坐").append(styleStart).append(site[1]).append(styleEnd);
					sb.append("从").append(styleStart).append(site[2]).append(styleEnd).append("出发，");
					if (!StringUtil.isNullStr(distance)) {
						sb.append("经").append(styleStart).append(distance).append(styleEnd).append("站，");
					}
					sb.append("到");
				}
				if (i > 0 && i < sites.size() - 1) {
					sb.append(styleStart).append(site[2]).append(styleEnd);
					sb.append("换乘").append(styleStart).append(site[1]).append(styleEnd).append("，");
					if (!StringUtil.isNullStr(distance)) {
						sb.append("经").append(styleStart).append(distance).append(styleEnd).append("站，");
					}
					sb.append("到");
				}
				if (i == sites.size() - 1) {
					sb.append(styleStart).append(site[2]).append(styleEnd).append("下车。");
				}
			}
			route.setRoute(sb.toString());
			routes.add(route);
		}
		if (routes.size() > 0) {
			Collections.sort(routes, new Comparator<Object>() {
				public int compare(Object o1, Object o2) {
					Route route1 = (Route) o1;
					Route route2 = (Route) o2;
					if (route1.getTransTime() > route2.getTransTime()) {
						return 1;
					}
					if (route1.getTransTime() < route2.getTransTime()) {
						return -1;
					}
					return 0;
				}
			});
		}
		return new PageResult(routes, page, rows);
	}

	/**
	 * 循环递归获取换乘方案
	 * @param result 换乘方案
	 * @param site 换乘方案中的一个线路信息
	 * @param all 所有线路
	 * @param past 已经计算过的公交线路
	 * @param curr 当前公交线路
	 * @param from 起点
	 * @param to 终点
	 * @param transTime 换乘次数
	 */
	@SuppressWarnings("unchecked")
	private void getRoutes(List<List<String[]>> result, List<String[]> site, List<Bus> all, List<Bus> past, Bus curr,
			String from, String to, int transTime) {
		if (site == null) {
			site = new ArrayList<String[]>();
		}
		if (transTime == 4) {
			return;
		}
		site.add(new String[] { curr.getId(), curr.getBusCode(), from });
		Set<String> stations = getStations(curr);
		if (stations.contains(to)) {
			site.add(new String[] { curr.getId(), curr.getBusCode(), to });
			result.add(site);
			return;
		}
		for (String station : stations) {
			if (!station.equals(from)) {
				List<Bus> fromBuses = new ArrayList<Bus>();
				for (Bus bus : all) {
					if (!past.contains(bus) && getStations(bus).contains(station)) {
						fromBuses.add(bus);
					}
				}
				for (Bus bus : fromBuses) {
					past.add(bus);
					getRoutes(result, cloneList(site), all, cloneList(past), bus, station, to, transTime + 1);
				}
			}
		}
	}

	/**
	 * 获取公交线路所有站点
	 * @param bus
	 * @return
	 */
	private Set<String> getStations(Bus bus) {
		Set<String> set = new HashSet<String>();
		List<String> upStations = Arrays.asList(bus.getUpStations().split("-"));
		List<String> downStations = Arrays.asList(bus.getDownStations().split("-"));
		set.addAll(upStations);
		set.addAll(downStations);
		return set;
	}

	/**
	 * 复制站点集合
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List cloneList(List list) {
		List rtn = new ArrayList();
		rtn.addAll(list);
		return rtn;
	}

	/**
	 * 获取统同一公交线路两站相距的站点数
	 * @param bus 当前公交线路
	 * @param from 起点站
	 * @param to 终点站
	 * @return
	 */
	private int getSiteDistance(Bus bus, String from, String to) {
		List<String> upStations = Arrays.asList(bus.getUpStations().split("-"));
		List<String> downStations = Arrays.asList(bus.getDownStations().split("-"));
		if (upStations.contains(from) && upStations.contains(to)) {
			int fromIndex = upStations.indexOf(from);
			int toIndex = upStations.indexOf(to);
			if (fromIndex <= toIndex) {
				return toIndex - fromIndex;
			} else if (toIndex == 0 && upStations.get(0).equals(upStations.get(upStations.size() - 1))) {
				return fromIndex < upStations.size() - 1 - fromIndex ? fromIndex : upStations.size() - 1 - fromIndex;
			}
		}
		if (downStations.contains(from) && downStations.contains(to)) {
			int fromIndex = downStations.indexOf(from);
			int toIndex = downStations.indexOf(to);
			if (fromIndex <= toIndex) {
				return toIndex - fromIndex;
			}
		}
		if (upStations.contains(from) && downStations.contains(to)) {
			List<String> upAll = new ArrayList<String>();
			upAll.addAll(downStations);
			upAll.addAll(1, upStations);
			upAll.remove(0);
			int fromIndex = upAll.indexOf(from);
			int toIndex = upAll.indexOf(to);
			if (fromIndex <= toIndex) {
				return toIndex - fromIndex;
			}
		}
		if (upStations.contains(to) && downStations.contains(from)) {
			List<String> downAll = new ArrayList<String>();
			downAll.addAll(upStations);
			downAll.addAll(1, downStations);
			downAll.remove(0);
			int fromIndex = downAll.indexOf(from);
			int toIndex = downAll.indexOf(to);
			if (fromIndex <= toIndex) {
				return toIndex - fromIndex;
			}
		}
		return -1;
	}

	public List<String> getStations() {
		return busDao.getStations();
	}

	public List<String> getBusCodes() {
		return busDao.getBusCodes();
	}
}
