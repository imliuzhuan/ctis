package com.ctis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CharsetEncodingFilter implements Filter {
	private Log logger = LogFactory.getLog(CharsetEncodingFilter.class);
	private String charset;

	public void init(FilterConfig config) throws ServletException {
		logger.info("##### filter.CharsetEncodingFilter.init() #####");
		// 取得初始化参数
		this.charset = config.getInitParameter("charset");
		if (charset == null) {
			charset = "UTF-8";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		logger.info("##### filter.CharsetEncodingFilter.doFilter() #####");
		// 设置字符集
		request.setCharacterEncoding(charset);
		// 继续执行
		chain.doFilter(request, response);
	}

	public void destroy() {
		logger.info("##### filter.CharsetEncodingFilter.destroy() #####");
	}

}
