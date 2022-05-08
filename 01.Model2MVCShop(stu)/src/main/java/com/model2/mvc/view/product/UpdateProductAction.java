package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action {

	public UpdateProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String menu = request.getParameter("menu");
		System.out.println(request.getParameter("prodNo") + " :: updateProductAction1");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println(prodNo + " :: updateProductAction2");
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setProdNo(prodNo);
		
		System.out.println("update ProductVO »Æ¿Œ :: "+productVO);
		
		ProductService productService = new ProductServiceImpl();
		productService.updateProduct(productVO);
		
//		HttpSession session = request.getSession();
//		session.setAttribute("productVO", productVO);
		
		return "redirect:/getProduct.do?prodNo="+prodNo+"&menu=ok";
	}

}
