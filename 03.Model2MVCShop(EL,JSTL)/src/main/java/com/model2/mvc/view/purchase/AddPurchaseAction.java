package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Product product = (Product)session.getAttribute("product");
		System.out.println(getClass().getName() + " :: user = "+user);
		System.out.println(getClass().getName() + " :: product = "+product);
		
		Purchase purchase = new Purchase();
		if (user == null) {
			user = new User();
		}
		purchase.setBuyer(user);
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setPurchaseProd(product);
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setMemberCheck(request.getParameter("memberCheck"));
		purchase.setTranCode("1");
		
		System.out.println("AddPurchase :: " + purchase);
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		purchaseService.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:purchase/addPurchase.jsp";
	}
}
