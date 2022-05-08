package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SearchVO searchVO = new SearchVO();
		UserVO userVO = (UserVO)session.getAttribute("user");
		
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		 String pageUnit = getServletContext().getInitParameter("pageSize");
	     searchVO.setPageUnit(Integer.parseInt(pageUnit));
	     searchVO.setPage(page);
	    
		String id = "";
		if (userVO != null) {
			id = userVO.getUserId();
		}
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		HashMap<String, Object> map = purchaseService.getPurchaseList(searchVO, id);
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
