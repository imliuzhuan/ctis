package com.ctis.util;

/**
 * 识别字符是数字还是字母工具类
 * @author liqq
 *
 */
public class DiscernUtil {
	/**
	 * 判断字符是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符是否是字母
	 * @param str
	 * @return
	 */
	public static boolean isLetter(String str) {
		char c = str.charAt(0);
		if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
			return true;
		} else {
			return false;
		}
	}
}
