package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class GetPurchaseAction extends Action {

	public GetPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		PurchaseVO purchaseVO = purchaseService.getPurchase(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/getPurchase.jsp";
	}
}
