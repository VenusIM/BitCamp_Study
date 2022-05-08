package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

public class ActionServlet extends HttpServlet {

	private RequestMapping mapper;

	//init(ServletConfig)를 Override할 수 있음
	@Override
	public void init() throws ServletException {
		super.init();
		// servlet이 시작할 때 필요한 정보가 담긴 ServletConfig를 반환
		// web.xml에 지정해준 init-param resources를 받아와 실행한 페이지 URI를 resources에 담아줌
		String resources = getServletConfig().getInitParameter("resources");
		System.out.println(resources + "의 값을 확인해봅시당");
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
		System.out.println("url 확인 :: " + url + "\ncontextpath 확인 :: " + contextPath + "\npath 확인 :: " + path);

		try {
			//실행할 action(controller) 생성
			Action action = mapper.getAction(path);
			//getServletContext()로 루트의 경로 가저와서 action에 저장
			action.setServletContext(getServletContext());

			//어떤 방식(navigation)으로 어떤 View를 실행할 것인지 execute를 통해 반환
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