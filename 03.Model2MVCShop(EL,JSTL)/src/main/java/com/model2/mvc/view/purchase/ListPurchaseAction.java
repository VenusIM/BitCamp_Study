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
		
		// web.xml  meta-data �� ���� ��� ���� 
	    int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
	    int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
	    search.setPageSize(pageSize);
	    
		String id = "";
		if (user != null) {
			id = user.getUserId();
		} else {
			//��ȸ�� ������ȸ
			search.setSearchName(request.getParameter("guestName"));
			search.setSearchPhone(request.getParameter("guestPhone"));
			id = null;
		}
		
		PurchaseService purchaseService = new PurchaseServlceImpl();
		Map<String, Object> map = purchaseService.getPurchaseList(search, id);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	    System.out.println("ListProductAction ::"+resultPage);
		
		System.out.println(map + " :: hashMap Ȯ��");
	    System.out.println(search + " :: search Ȯ��");
		
		// Model �� View ����
	    request.setAttribute("list", map.get("list"));
	    request.setAttribute("resultPage", resultPage);
	    request.setAttribute("search", search);
		
	    
	    //���� ��ȸ���� �� totalCount�� 0�̶��..? �ٽ� �˻��ϵ���!
	    //��ȸ�� �� listPurchase.jsp�� �ʿ���� ���̺� �����
	    
		return "forward:purchase/listPurchase.jsp";
	}
}
