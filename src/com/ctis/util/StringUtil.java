package com.ctis.util;

public class StringUtil {
	/**
	 * 判断是否是空字符串
	 * @param s
	 * @return
	 */
	public static boolean isNullStr(String s) {
		if (s == null || s.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将Object对象转变为String类型对象，对于null值返回空字符串.
	 *
	 * @param inObj 待处理的对象.
	 */
	public static String killNull(Object inObj) {
		if (inObj == null) {
			return "";
		}
		return inObj.toString().trim();
	}

	/**
	 * 将Object对象转变为String类型对象，对于null值返回缺省字符串.
	 *
	 * @param inObj 待处理的对象.
	 */
	public static String killNull(Object inObj, String toStr) {
		if (inObj == null) {
			return toStr;
		}
		return inObj.toString().trim();
	}
}
