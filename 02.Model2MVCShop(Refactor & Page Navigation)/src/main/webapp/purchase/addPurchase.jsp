<%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
	System.out.println(getClass().getName() + " :: purchase = "+ purchase);
	
	User user = purchase.getBuyer();
	System.out.println(getClass().getName() + " :: user = "+ user);
	
	Product product = purchase.getPurchaseProd();
	System.out.println(getClass().getName() + " :: productVO = "+ product);
	
	String payment = "�ſ뱸��";
%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%= product.getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%= user.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<% if ("1".equals(purchase.getPaymentOption())) { 
					payment = "���ݱ���";
				}
		%>
		<td><%= payment %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= purchase.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%= purchase.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%= purchase.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%= purchase.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%= purchase.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>