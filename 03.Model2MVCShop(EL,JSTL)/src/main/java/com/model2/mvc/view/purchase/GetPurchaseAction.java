package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;

public class GetPurchaseAction extends Action {

	public GetPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		HttpSession session = request.getSession();
		User User = (User) session.getAttribute("user");
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:purchase/getPurchase.jsp";
	}
}
