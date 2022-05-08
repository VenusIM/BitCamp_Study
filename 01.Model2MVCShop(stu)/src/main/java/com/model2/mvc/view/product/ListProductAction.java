package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
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
      SearchVO searchVO = new SearchVO();
      
      int page = 1;
      System.out.println(request.getParameter("page") + " :: page값 출력");
      if (request.getParameter("page") != null) {
         page = Integer.parseInt(request.getParameter("page"));
      } 
      
      HttpSession session = request.getSession();
      String search = (String) session.getAttribute("search");
      String cond = (String) session.getAttribute("condition");
      
      if (search != null && request.getParameter("page") == null) {
         session.removeAttribute("search");
         session.removeAttribute("condition");
         System.out.println("search remove!!");
      } 
      
      searchVO.setPage(page);
      searchVO.setSearchCondition(request.getParameter("searchCondition"));
      searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
      
      if (session.getAttribute("search") != null){
         System.out.println("search session 존재 : " + search +"!!");
         searchVO.setSearchKeyword(search);
         searchVO.setSearchCondition(cond);
         System.out.println("condition session 존재 : " + cond +"!!");         
      }
      
      String pageUnit = getServletContext().getInitParameter("pageSize");
      searchVO.setPageUnit(Integer.parseInt(pageUnit));
      
      ProductService productService = new ProductServiceImpl();
      HashMap<String, Object> hashMap = productService.getProductList(searchVO);
      
      System.out.println(hashMap + " :: hashMap 확인");
      System.out.println(searchVO + " :: searchVO 확인");
      
      request.setAttribute("map", hashMap);
      request.setAttribute("searchVO", searchVO);
      request.setAttribute("page", page);
      
      return "forward:/product/listProduct.jsp";
   }
}