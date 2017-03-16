package com.ctis.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Constant {
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
	private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	private final static NumberFormat numberFormat = new DecimalFormat("0000");
	private static int seq = 0;
	private static final int MAX = 9999;

	/**
	* 时间格式生成序列
	* @return String
	*/
	public static synchronized String generateSequenceNo() {
		Calendar rightNow = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
		numberFormat.format(seq, sb, HELPER_POSITION);
		if (seq == MAX) {
			seq = 0;
		} else {
			seq++;
		}
		return sb.toString();
	}

	/**
	 * 得到Excel文件绝对路径
	 * @return
	 */
	public static String getTmpFilePath() {
		return getRootPath() + "/bus/tmp/hsgjxl_tmp.xls";
	}

	/**
	 * 得到发布根目录
	 * @return
	 */
	public static String getRootPath() {
		// file:/D:/ResumeParser/WebRoot/WEB-INF/classes/util/Application.class
		String result = Constant.class.getClassLoader().getResource("/").getPath();
		int index = result.indexOf("WEB-INF");
		if (index == -1) {
			index = result.indexOf("bin");
		}
		result = result.substring(0, index);
		if (result.startsWith("jar")) {
			// 当class文件在jar文件中时，返回"jar:file:/F:/ ..."样的路径
			result = result.substring(10);
		} else if (result.startsWith("file")) {
			// 当class文件在class文件中时，返回"file:/F:/ ..."样的路径
			result = result.substring(6);
		}
		if (result.endsWith("/")) {
			result = result.substring(0, result.length() - 1);// 不包含最后的"/"
		}
		Properties props = System.getProperties(); //获得系统属性集    
		String osName = props.getProperty("os.name"); //操作系统名称    
		if ("Linux".equals(osName)) {
			result = "/" + result;
		}
		return result;
	}

	/**
	 * 获取以时间格式化的字符串作为ID值
	 * @return
	 */
	public static String getSeries() {
		return generateSequenceNo();
	}
}
