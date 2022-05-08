<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.common.Search"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
        
<%
	List<Purchase> list = (List<Purchase>)request.getAttribute("list");
	Page resultPage = (Page)request.getAttribute("resultPage");

	Search search = (Search)request.getAttribute("search");
	
	User user = (User)session.getAttribute("user");
%>

<html>
<head>
<title>구매 목록조회</title>

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
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%= resultPage.getTotalCount() %> 건수, 현재 <%= resultPage.getCurrentPage() %> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%
		int no = list.size();
		 for (int i = 0; i < list.size(); i++) {
	         Purchase vo = (Purchase) list.get(i);
	%>
		<tr class="ct_list_pop">
			<td align="center">
				<a href="/getPurchase.do?tranNo=<%= vo.getTranNo() %>"><%= no-- %></a>
			</td>
			<td></td>
			<td align="left">
				<a href="/getUser.do?userId=<%= user.getUserId() %>"><%= user.getUserId() %></a>
			</td>
			<td></td>
			<td align="left"><%= user.getUserName() %></td>
			<td></td>
			<td align="left"><%= user.getPhone() %></td>
			<td></td>
			<% if("1".equals(vo.getTranCode())) { %>
					<td align="left">현재 구매완료 상태 입니다.</td>
			<% } else if("2".equals(vo.getTranCode())) { %>
					<td align="left">현재 배송중 상태 입니다.</td>
			<% } else { %>
					<td align="left">현재 배송완료 상태 입니다.</td>
			<% } %>
			<td></td>
			<% if("2".equals(vo.getTranCode())) { %>
				<td align="left"><a href='/updateTranCodeByProd.do?prodNo=<%= vo.getPurchaseProd().getProdNo() %>&tranCode=3'>물건도착</a></td>
			<% } else { %>
				<td align="left"></td>
			<% } %>
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>
			
			<% for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
				<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
			<% } %>
		
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>