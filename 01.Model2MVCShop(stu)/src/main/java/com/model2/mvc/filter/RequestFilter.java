package com.model2.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//공통, 선처리 => Filter Interface
public class RequestFilter implements Filter{

	public void init(FilterConfig arg0) throws ServletException {
	}

	//request, response로 요청과 응답을 조작, FilterChain을 통해 조작 이후 요청을 원래 목적지인 Servlet으로 전달
	public void doFilter(ServletRequest arg0, ServletResponse arg1,	FilterChain arg2) 
			throws IOException, ServletException {
		arg0.setCharacterEncoding("euc-kr");
		arg2.doFilter(arg0, arg1);
	}

	public void destroy() {
	}
}