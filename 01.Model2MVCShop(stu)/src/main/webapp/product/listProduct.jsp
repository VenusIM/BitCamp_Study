<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page
   import="com.model2.mvc.service.purchase.impl.PurchaseServlceImpl"%>
<%@page import="com.model2.mvc.service.purchase.PurchaseService"%>
<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>

<%@ page import="java.util.*"%>
<%@ page import="com.model2.mvc.service.user.vo.*"%>
<%@ page import="com.model2.mvc.common.SearchVO"%>

<%
   HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("map");
   SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");
   UserVO userVO = (UserVO) session.getAttribute("user");
      
   String search = searchVO.getSearchKeyword();
   
   if(search != null) {
      session.setAttribute("search", search);
      session.setAttribute("condition", searchVO.getSearchCondition());
      
      System.out.println("search :: " + search + ", condition :: " + searchVO.getSearchCondition());
   }
   
   String role = null;
   if (userVO != null) {
      role = userVO.getRole();
   }
   
   String menu = request.getParameter("menu");
   System.out.println("menu :: "+ menu);
   
   int total = 0;
   ArrayList<ProductVO> list = null;
   if (map != null) {
      total = ((Integer) map.get("count")).intValue();
      list = (ArrayList<ProductVO>) map.get("list");
      System.out.println(total + " :: total, " + list + " :: list");
   }
   
   int currentPage = searchVO.getPage();
   
   int totalPage = 0;
   if (total > 0) {
      totalPage = total / searchVO.getPageUnit();
      if (total % searchVO.getPageUnit() > 0)
         totalPage += 1;
   }
   System.out.println(totalPage + " :: totalPage");
%>

<html>
<head>

<%
if ("manage".equals(menu)) {
%>
<title>상품 관리</title>
<%
} else {
%>
<title>상품 목록조회</title>
<%
}
%>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
   function fncGetProductList() {
      document.detailForm.submit();
   }
   -->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

   <div style="width: 98%; margin-left: 10px;">

      <form name="detailForm" action="/listProduct.do?menu=<%= menu %>"
         method="post">

         <table width="100%" height="37" border="0" cellpadding="0"
            cellspacing="0">
            <tr>
               <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
                  width="15" height="37" /></td>
               <td background="/images/ct_ttl_img02.gif" width="100%"
                  style="padding-left: 10px;">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                     <tr>
                        <%
                        if ("manage".equals(menu)) {
                        %>
                        <td width="93%" class="ct_ttl01">상품 관리</td>
                        <%
                        } else {
                        %>
                        <td width="93%" class="ct_ttl01">상품 목록조회</td>
                        <%
                        }
                        %>
                     </tr>
                  </table>
               </td>
               <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
                  width="12" height="37" /></td>
            </tr>
         </table>


         <table width="100%" border="0" cellspacing="0" cellpadding="0"
            style="margin-top: 10px;">
            <tr>

               <td align="right"><select name="searchCondition"
                  class="ct_input_g" style="width: 80px">
                     <option value="0">상품번호</option>
                     <option value="1">상품명</option>
                     <option value="2">상품가격</option>
               </select> <input type="text" name="searchKeyword" class="ct_input_g"
                  style="width: 200px; height: 19px" /></td>


               <td align="right" width="70">
                  <table border="0" cellspacing="0" cellpadding="0">
                     <tr>
                        <td width="17" height="23"><img
                           src="/images/ct_btnbg01.gif" width="17" height="23"></td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01"
                           style="padding-top: 3px;"><a
                           href="javascript:fncGetProductList();">검색</a></td>
                        <td width="14" height="23"><img
                           src="/images/ct_btnbg03.gif" width="14" height="23"></td>
                     </tr>
                  </table>
               </td>
            </tr>
         </table>


         <table width="100%" border="0" cellspacing="0" cellpadding="0"
            style="margin-top: 10px;">
            <tr>
               <td colspan="11">전체 <%=total%> 건수, 현재 <%=currentPage%> 페이지
               </td>
            </tr>
            <tr>
               <td class="ct_list_b" width="100">No</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">상품명</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">가격</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">등록일</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">현재상태</td>
            </tr>
            <tr>
               <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>

            <%
            int no = list.size();
            for (int i = 0; i < list.size(); i++) {
               ProductVO vo = (ProductVO) list.get(i);
            %>

            <tr class="ct_list_pop">
               <td align="center"><%=no--%></td>
               <td></td>
               <td align="left">
                   <% if (("user".equals(role) || role == null) && vo.getProTranCode() != null) { %>
                  		<%=vo.getProdName()%> 
                  <% } else { %> 
                  <a href="getProduct.do?prodNo=<%=vo.getProdNo()%>&menu=<%= menu%>"><%=vo.getProdName()%></a>
                  <% } %>
               </td>
               <td></td>
               <td align="left"><%=vo.getPrice()%></td>
               <td></td>
               <td align="left"><%=vo.getRegDate()%></td>
               <td></td>
               <%  System.out.println("role :: "+role+", tranCode :: "+vo.getProTranCode()+", menu :: "+menu); %>
               <% if (vo.getProTranCode() == null) { %>
               		<td align="left">판매중</td>
               <% } else { %> <%
            	     if ("admin".equals(role)) {
               			if ("1".equals(vo.getProTranCode())) {
							if ("manage".equals(menu)) { %>
               					<td align="left">구매완료<a href='/updateTranCodeByProd.do?prodNo=<%=vo.getProdNo()%>&tranCode=2'>배송하기</a></td>
						 <% } else {%>
               					<td align="left">구매완료</td>
               		 	 <% } %>
	               	 <% } else if ("2".equals(vo.getProTranCode())) { %>
	               			<td align="left">배송중</td>
	               	 <% } else { %>
	               			<td align="left">배송완료</td>
	               	 <% } %> 
	              <% } else { %>
	               		<td align="left">재고없음</td>
	              <% } %>
               <% } %>
            </tr>
            <tr>
               <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>
            <%
            }
            %>
            <!-- for문종료 -->
         </table>

         <table width="100%" border="0" cellspacing="0" cellpadding="0"
            style="margin-top: 10px;">
            <tr>
               <td align="center">
                  <% for (int i = 1; i <= totalPage; i++) { %> 
                  <a href="/listProduct.do?page=<%=i%>&menu=<%=menu%>"><%=i%></a>
                  <% } %>
               </td>
            </tr>
         </table>
         <!--  페이지 Navigator 끝 -->
      </form>

   </div>
</body>
</html>