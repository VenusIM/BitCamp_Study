package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//~Action.java�� ����� �� execute ����
//���� ó��
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
	
	//Action �ʼ� ����
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}