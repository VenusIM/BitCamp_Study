package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");
		ProductVO productVO = (ProductVO)session.getAttribute("productVO");
		System.out.println(getClass().getName() + " :: userVO = "+userVO);
		System.out.println(getClass().getName() + " :: productVO = "+productVO);
		
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setBuyer(userVO);
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));	
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));					
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setTranCode("1");
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("prodNo")));
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		purchaseService.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}
}
