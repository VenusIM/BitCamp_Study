<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.common.Search"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
	List<Purchase> list = (List<Purchase>)request.getAttribute("list");
	Page resultPage = (Page)request.getAttribute("resultPage");

	Search search = (Search)request.getAttribute("search");
	
	User user = (User)session.getAttribute("user");
%>
--%>  
<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<c:choose>
			<c:when test="${empty user}">
				<td class="ct_list_b" width="150">�̸�</td>
			</c:when>
			<c:otherwise>
				<td class="ct_list_b" width="150">ȸ��ID</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">ȸ����</td>						
			</c:otherwise>
		</c:choose>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%--
	<%
		int no = list.size();
		 for (int i = 0; i < list.size(); i++) {
	         Purchase vo = (Purchase) list.get(i);
	%>
	--%>
	<c:set var="i" value="0"/>
	<c:forEach var="vo" items="${list}" >
		<c:set var="i" value="${i+1}"/>
		<tr class="ct_list_pop">
			<td align="center">
				<a href="/getPurchase.do?tranNo=${vo.tranNo}">${i}</a>
			</td>
			<td></td>
			<c:choose>
				<c:when test="${empty user}">
					<td align="left">${vo.receiverName}</td>
					<td></td>
					<td align="left">${vo.receiverPhone}</td>
					<td></td>
				</c:when>
				<c:otherwise>
					<td align="left">
						<a href="/getUser.do?userId=${user.userId}">${user.userId}</a>
					</td>
					<td></td>
					<td align="left">${user.userName}</td>
					<td></td>
					<td align="left">${user.phone}</td>
					<td></td>
				</c:otherwise>
			</c:choose>
			
			<%--
				<% if("1".equals(vo.getTranCode())) { %>
						<td align="left">���� ���ſϷ� ���� �Դϴ�.</td>
				<% } else if("2".equals(vo.getTranCode())) { %>
						<td align="left">���� ����� ���� �Դϴ�.</td>
				<% } else { %>
						<td align="left">���� ��ۿϷ� ���� �Դϴ�.</td>
				<% } %>
			--%>
			<c:if test="${vo.tranCode == 1}">
				<td align="left">���� ���ſϷ� ���� �Դϴ�.</td>
			</c:if>
			<c:if test="${vo.tranCode == 2}">
				<td align="left">���� ����� ���� �Դϴ�.</td>
			</c:if>
			<c:if test="${vo.tranCode == 3}">
				<td align="left">���� ��ۿϷ� ���� �Դϴ�.</td>
			</c:if>
			<td></td>
			<c:choose>
				<c:when test="${vo.tranCode == 2}">
					<td align="left"><a href='/updateTranCodeByProd.do?prodNo=${vo.purchaseProd.prodNo}&tranCode=3'>���ǵ���</a></td>
				</c:when>
				<c:otherwise>
					<td align="left"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
		 <%--
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>
			
			<% for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
				<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
			<% } %>
		
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %>
		 --%>
		 	<jsp:include page="../common/pageNavigator.jsp">
		 		<jsp:param value="fncGetPurchaseList" name="page"/>
		 	</jsp:include>
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>