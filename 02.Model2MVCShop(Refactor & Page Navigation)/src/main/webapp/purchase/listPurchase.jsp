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
		<td colspan="11">��ü <%= resultPage.getTotalCount() %> �Ǽ�, ���� <%= resultPage.getCurrentPage() %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
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
					<td align="left">���� ���ſϷ� ���� �Դϴ�.</td>
			<% } else if("2".equals(vo.getTranCode())) { %>
					<td align="left">���� ����� ���� �Դϴ�.</td>
			<% } else { %>
					<td align="left">���� ��ۿϷ� ���� �Դϴ�.</td>
			<% } %>
			<td></td>
			<% if("2".equals(vo.getTranCode())) { %>
				<td align="left"><a href='/updateTranCodeByProd.do?prodNo=<%= vo.getPurchaseProd().getProdNo() %>&tranCode=3'>���ǵ���</a></td>
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
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>