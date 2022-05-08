package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	public UpdateTranCodeByProdAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String tranCode = request.getParameter("tranCode");
		
		System.out.println("상품번호 :: "+prodNo + ", code :: "+tranCode);
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		//PurchaseVO purchaseVO = purchaseService.getPurchase2(prodNo);
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		purchaseVO.setTranCode(tranCode);
		purchaseVO.setPurchaseProd(productVO);
		purchaseService.updateTranCode(purchaseVO);
		
		if ("2".equals(tranCode)) {
			return "forward:/listProduct.do?menu=manage";
		} 
				
		return "forward:/listPurchase.do";
	}
}
