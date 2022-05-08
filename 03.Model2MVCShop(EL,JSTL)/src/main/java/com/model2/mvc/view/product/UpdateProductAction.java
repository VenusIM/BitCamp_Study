package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action {

	public UpdateProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(request.getParameter("prodNo") + " :: updateProductAction1");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println(prodNo + " :: updateProductAction2");
		
		Product product = new Product();
		
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate").replace("-", ""));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		product.setProdNo(prodNo);
		
		System.out.println("update Product »Æ¿Œ :: "+product);
		
		ProductService productService = new ProductServiceImpl();
		productService.updateProduct(product);
		
		return "redirect:./getProduct.do?prodNo="+prodNo+"&menu=ok";
	}

}
