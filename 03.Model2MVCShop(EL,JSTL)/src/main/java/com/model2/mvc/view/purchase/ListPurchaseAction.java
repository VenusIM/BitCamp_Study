package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServlceImpl;

public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Search search = new Search();
		User user = (User)session.getAttribute("user");
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null && !"".equals(request.getParameter("currentPage"))){
	      currentPage=Integer.parseInt(request.getParameter("currentPage"));
	    }
		
		search.setCurrentPage(currentPage);
		
		// web.xml  meta-data 로 부터 상수 추출 
	    int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
	    int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
	    search.setPageSize(pageSize);
	    
		String id = "";
		if (user != null) {
			id = user.getUserId();
		} else {
			//비회원 구매조회
			search.setSearchName(request.getParameter("guestName"));
			search.setSearchPhone(request.getParameter("guestPhone"));
			id = null;
		}
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		Map<String, Object> map = purchaseService.getPurchaseList(search, id);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	    System.out.println("ListProductAction ::"+resultPage);
		
		System.out.println(map + " :: hashMap 확인");
	    System.out.println(search + " :: search 확인");
		
		// Model 과 View 연결
	    request.setAttribute("list", map.get("list"));
	    request.setAttribute("resultPage", resultPage);
	    request.setAttribute("search", search);
		
	    
	    //만약 비회원일 시 totalCount가 0이라면..? 다시 검색하도록!
	    //비회원 시 listPurchase.jsp에 필요없는 테이블 지우기
	    
		return "forward:purchase/listPurchase.jsp";
	}
}
