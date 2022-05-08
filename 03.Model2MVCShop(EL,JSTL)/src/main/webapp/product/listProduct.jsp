<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<%@ page import="java.util.*"%>
<%@ page import="com.model2.mvc.common.*"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Product"%>
<%
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	//==> null �� ""(nullString)���� ����
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
   	User user = (User) session.getAttribute("user");
   	
   	System.out.println("searchCondition :: "+ searchCondition);
   
   String role = null;
   if (user != null) {
      role = user.getRole();
   }
   
   String menu = request.getParameter("menu");
   System.out.println("menu :: "+ menu);
%>
 --%>

<html>
<head>
<%--
<%
if ("manage".equals(menu)) {
%>
<title>��ǰ ����</title>
<%
} else {
%>
<title>��ǰ �����ȸ</title>
<%
}
%>
--%>

<c:choose>
	<c:when test="${param.menu == 'manage'}">
		<title>��ǰ ����</title>
	</c:when>
	<c:otherwise>
		<title>��ǰ �����ȸ</title>
	</c:otherwise>
</c:choose>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
   function fncGetProductList(currentPage) {
	  document.getElementById("currentPage").value = currentPage;
      document.detailForm.submit();
   }
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

   <div style="width: 98%; margin-left: 10px;">

      <form name="detailForm" action="listProduct.do?menu=${param.menu}"
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
                     <%--
                        <%
                        if ("manage".equals(menu)) {
                        %>
                        <td width="93%" class="ct_ttl01">��ǰ ����</td>
                        <%
                        } else {
                        %>
                        <td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
                        <%
                        }
                        %>
                     --%>
                     	<c:choose>
                     		<c:when test="${param.menu == 'manage'}">
	                     		<td width="93%" class="ct_ttl01">��ǰ ����</td>                     		
                     		</c:when>
                     		<c:otherwise>
                     			<td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
                     		</c:otherwise>
                     	</c:choose>
                     </tr>
                  </table>
               </td>
               <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
                  width="12" height="37" /></td>
            </tr>
         </table>

         <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
            <tr>
               <td align="right">
	               <select name="searchCondition" class="ct_input_g" style="width: 80px">
	                  <%--
	                     <option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>��ǰ��ȣ</option>
	                     <option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>��ǰ��</option>
	                     <option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>��ǰ����</option>
	                     </select> <input type="text" name="searchKeyword" value="<%= searchKeyword %>" class="ct_input_g"
	                  --%>
	                  <c:if test="${!empty user && user.userId == 'admin'}">
	                    <option value="0" ${search.searchCondition == '0' ? 'selected' : ''}>��ǰ��ȣ</option>             
	                  </c:if>
	                    <option value="1" ${search.searchCondition == '1' ? 'selected' : ''}>��ǰ��</option>
	               		<option value="2" ${search.searchCondition == '2' ? 'selected' : ''}>��ǰ����</option>
	               </select> 
               		<input type="text" name="searchKeyword" value="${search.searchKeyword}" class="ct_input_g" style="width: 200px; height: 19px" />
               </td>

               <td align="right" width="70">
                  <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="17" height="23">
                        	<img src="/images/ct_btnbg01.gif" width="17" height="23">
                        </td>
                        <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
                           	<a href="javascript:fncGetProductList('1');">�˻�</a>
                        </td>
                        <td width="14" height="23">
                        	<img src="/images/ct_btnbg03.gif" width="14" height="23">
                        </td>
                    </tr>
                  </table>
               </td>
            </tr>
         </table>


         <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
            <tr>
               <td colspan="8">
               		<%-- ��ü <%= resultPage.getTotalCount() %> �Ǽ�, ���� <%= resultPage.getCurrentPage() %> ������ --%>
               		��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������ 
               </td>
               <c:if test="${param.menu != 'manage'}">
	               <td align="right">
	               		 <input type="hidden" id="searchOrder" name="searchOrder" value="${search.searchOrder}"/>
			 			<a href="listProduct.do?menu=${param.menu}&searchOrder=1">��ǰ��</a> | <a href="listProduct.do?menu=${param.menu}&searchOrder=2">��������</a> | <a href="listProduct.do?menu=${param.menu}&searchOrder=3">��������</a>
	               </td>               
               </c:if>
            </tr>
            <tr>
               <td class="ct_list_b" width="100">No</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">��ǰ��</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">����</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">�����</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">�������</td>
            </tr>
            <tr>
               <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>

            <%--
            <%
            	for (int i = 0; i < list.size(); i++) {
               		Product product = list.get(i);
            %>
			
            <tr class="ct_list_pop">
               <td align="center"><%= i + 1 %></td>
               <td></td>
               <td align="left">
                  <% if (("user".equals(role) || role == null) && product.getProTranCode() != null) { %>
                  		<%=product.getProdName()%> 
                  <% } else { %> 
                  <a href="getProduct.do?prodNo=<%=product.getProdNo()%>&menu=<%= menu%>"><%=product.getProdName()%></a>
                  <% } %>
               </td>
               <td></td>
               <td align="left"><%=product.getPrice()%></td>
               <td></td>
               <td align="left"><%=product.getRegDate()%></td>
               <td></td>
               <%  System.out.println("role :: "+role+", tranCode :: "+product.getProTranCode()+", menu :: "+menu); %>
               <% if (product.getProTranCode() == null) { %>
               		<td align="left">�Ǹ���</td>
               <% } else { %> <%
            	     if ("admin".equals(role)) {
               			if ("1".equals(product.getProTranCode())) {
							if ("manage".equals(menu)) { %>
               					<td align="left">���ſϷ� <a href='/updateTranCodeByProd.do?prodNo=<%=product.getProdNo()%>&tranCode=2'>����ϱ�</a></td>
						 <% } else {%>
               					<td align="left">���ſϷ�</td>
               		 	 <% } %>
	               	 <% } else if ("2".equals(product.getProTranCode())) { %>
	               			<td align="left">�����</td>
	               	 <% } else { %>
	               			<td align="left">��ۿϷ�</td>
	               	 <% } %> 
	              <% } else { %>
	               		<td align="left">������</td>
	              <% } %>
               <% } %>
            </tr>
            <tr>
               <td colspan="11" bgcolor="D6D7D6" height="1"></td>
            </tr>
            <%
            }
            %>
            <!-- for������ -->
            --%>
            <c:set var="i" value="0" />
            <c:forEach var="product" items= "${list}">
            	<c:set var="i" value="${i+1}"/>
				<tr class="ct_list_pop">
	               <td align="center">${i}</td>
	               <td></td>
	               <td align="left">
	               	  	<a href="getProduct.do?prodNo=${product.prodNo}&menu=${param.menu}&tranCode=${product.proTranCode}">${product.prodName}</a>
	               </td>
	               <td></td>
	               <td align="left">${product.price}</td>
	               <td></td>
	               <td align="left">${product.regDate}</td>
	               <td></td>
	               <c:choose>
	               		<c:when test="${empty product.proTranCode}">
	               			<td align="left">�Ǹ���</td>
	               		</c:when>
	               		<c:otherwise>
	               			<c:choose>
	               				<c:when test="${user.role == 'admin'}">
	               					<c:if test="${product.proTranCode == '1'}">
	               						<c:choose>
	               							<c:when test="${param.menu == 'manage'}">
	               								<td align="left">���ſϷ� <a href='/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2'>����ϱ�</a></td>
	               							</c:when>
	               							<c:otherwise>
	               								<td align="left">���ſϷ�</td>
	               							</c:otherwise>
	               						</c:choose>
				               		</c:if>
				               		<c:if test="${product.proTranCode == 2}">
				               			<td align="left">�����</td>
				               		</c:if>
				               		<c:if test="${product.proTranCode == 3}">
				               			<td align="left">��ۿϷ�</td>
				               		</c:if>
	               				</c:when>
	               				<c:otherwise>
	               					<td align="left">������</td>
	               				</c:otherwise>
	               			</c:choose>
	               		</c:otherwise>
	               </c:choose>
	               			
	               			
	            </tr>
	            <tr>
	               <td colspan="11" bgcolor="D6D7D6" height="1"></td>
	            </tr>
			</c:forEach>
         </table>

         <table width="100%" border="0" cellspacing="0" cellpadding="0"
            style="margin-top: 10px;">
            <tr>
               <td align="center">
                  
                  <input type="hidden" id="currentPage" name="currentPage" value=""/>
                  <%-- 
					<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
							�� ����
					<% }else{ %>
							<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
					<% } %>
		
					<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	
							System.out.println("listProduct " + i);%>
							<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
					<% } %>
			
					<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
							���� ��
					<% }else{ %>
							<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
					<% } %>
				 --%>
				 <jsp:include page="../common/pageNavigator.jsp">
				 	<jsp:param value="fncGetProductList" name="page"/>
				 </jsp:include>
               </td>
            </tr>
         </table>
         <!--  ������ Navigator �� -->
      </form>

   </div>
</body>
</html>