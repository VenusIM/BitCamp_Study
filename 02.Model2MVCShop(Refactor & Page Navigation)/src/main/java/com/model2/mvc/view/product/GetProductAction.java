package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {
	static String history="";

	public GetProductAction() {
		// TODO Auto-generated constructor stub
		System.out.println("GetProductAction");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String menu = request.getParameter("menu");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		//�ǸŻ�ǰ����(�Ķ���� menu=manage)�� ����� 
		//properties�� ����� key, value�� ���� UpdateProductViewAction.java�� �̵� 
		if ("manage".equals(menu)) {
			return "redirect:/updateProductView.do?prodNo="+prodNo+"&menu="+menu;
		}
		
		Product product = new Product();
		ProductService productService = new ProductServiceImpl();
		
		HttpSession session = request.getSession(true);
		
		//search �� �� ��Ű ���� .. history��� �̸��� ,�� �����Ͽ� �߰��� ���� 
		String pn = request.getParameter("prodNo");
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("history")) {
					boolean check = false;
					String[] strArray = cookies[i].getValue().split(",");
					if (strArray.length != 0) {
						for (String str:strArray) {
							if (str.equals(request.getParameter("prodNo"))) {
								pn = "";
								check = true;
								break;
							}
						}
						
						history = cookies[i].getValue();
						if (!check) {
							history = cookies[i].getValue()+",";
							pn = request.getParameter("prodNo");
						}
					}
				}
			}
		}
		
		Cookie cookie = new Cookie("history",  history+pn);
		response.addCookie(cookie);
		 
		product = productService.getProduct(prodNo);
		session.setAttribute("product", product);
		return "forward:/product/getProduct.jsp?prodNo="+prodNo+"&menu="+menu;
	}
}