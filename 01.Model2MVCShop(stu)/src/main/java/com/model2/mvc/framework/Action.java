package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//~Action.java에 상속해 줄 execute 구현
//공통 처리
public abstract class Action {
	
	private ServletContext servletContext;
	
	public Action(){
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	//Action 필수 행위
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}