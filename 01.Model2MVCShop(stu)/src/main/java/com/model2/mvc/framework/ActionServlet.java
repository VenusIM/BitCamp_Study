package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

public class ActionServlet extends HttpServlet {

	private RequestMapping mapper;

	//init(ServletConfig)�� Override�� �� ����
	@Override
	public void init() throws ServletException {
		super.init();
		// servlet�� ������ �� �ʿ��� ������ ��� ServletConfig�� ��ȯ
		// web.xml�� �������� init-param resources�� �޾ƿ� ������ ������ URI�� resources�� �����
		String resources = getServletConfig().getInitParameter("resources");
		System.out.println(resources + "�� ���� Ȯ���غ��ô�");
		mapper = RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /project/aaa.jsp
		// getRequestURI() => /aaa.jsp
		// getContextPath() => /project
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println("url Ȯ�� :: " + url + "\ncontextpath Ȯ�� :: " + contextPath + "\npath Ȯ�� :: " + path);

		try {
			//������ action(controller) ����
			Action action = mapper.getAction(path);
			//getServletContext()�� ��Ʈ�� ��� �����ͼ� action�� ����
			action.setServletContext(getServletContext());

			//� ���(navigation)���� � View�� ������ ������ execute�� ���� ��ȯ
			String resultPage = action.execute(request, response);
			String result = resultPage.substring(resultPage.indexOf(":") + 1);

			//Navigation
			if (resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}