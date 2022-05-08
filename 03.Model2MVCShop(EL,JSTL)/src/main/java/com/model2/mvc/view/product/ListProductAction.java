package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

   public ListProductAction() {
      // TODO Auto-generated constructor stub
   }

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      Search search = new Search();
      
      int currentPage=1;

      System.out.println(request.getParameter("currentPage") + "listPro");
      
      if(request.getParameter("currentPage") != null && !"".equals(request.getParameter("currentPage"))){
    	  currentPage=Integer.parseInt(request.getParameter("currentPage"));
      }
		
      search.setCurrentPage(currentPage);
      search.setSearchCondition(request.getParameter("searchCondition"));
      search.setSearchKeyword(request.getParameter("searchKeyword"));
      search.setSearchOrder(request.getParameter("searchOrder"));
      // web.xml  meta-data 로 부터 상수 추출 
      int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
      int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
      search.setPageSize(pageSize);
      
      ProductService productService = new ProductServiceImpl();
      Map<String, Object> hashMap = productService.getProductList(search);
      
      System.out.println("Condition :: "+request.getParameter("searchCondition"));
      System.out.println("Keyword :: "+request.getParameter("searchKeyword"));
      System.out.println("Order :: "+request.getParameter("searchOrder"));
      
      Page resultPage = new Page(currentPage, ((Integer)hashMap.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println("ListProductAction ::"+resultPage);
      
      System.out.println(hashMap + " :: hashMap 확인");
      System.out.println(search + " :: search 확인");
      
      // Model 과 View 연결
      request.setAttribute("list", hashMap.get("list"));
      request.setAttribute("resultPage", resultPage);
      request.setAttribute("search", search);
      
      return "forward:product/listProduct.jsp";
   }
}