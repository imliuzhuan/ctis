package com.ctis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author liqq
 *
 */
public class DateUtil {
	/**
	 * 返回当前日期字符串 yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDateString() {
		return dateToString(getCurrentDateTime(), "yyyy-MM-dd");
	}

	/**
	 * 返回当前时间字符串 yyyy-MM-dd hh24:mm:ss
	 * @return
	 */
	public static String getCurrentTimeString() {
		return dateToString(getCurrentDateTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/** 根据时间变量返回时间字符串
	 * @return 返回时间字符串
	 * @param pattern 时间字符串样式
	 * @param date 时间变量
	 */
	public static String dateToString(Date date, String pattern) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/** 返回当前时间
	 * @return 返回当前时间
	 */
	public static Date getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		return date;
	}
}
