<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@page import="com.model2.mvc.service.product.vo.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
	System.out.println(getClass().getName() + " :: purchaseVO = "+ purchaseVO);
	
	UserVO userVO = purchaseVO.getBuyer();
	System.out.println(getClass().getName() + " :: userVO = "+ userVO);
	
	ProductVO productVO = purchaseVO.getPurchaseProd();
	System.out.println(getClass().getName() + " :: productVO = "+ productVO);
	
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
		<td><%= purchaseVO.getTranNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%= userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<% if ("1".equals(purchaseVO.getPaymentOption().trim())) { 
					payment = "���ݱ���";
				}
		%>
		<td><%= payment %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%= purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%= purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%= purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%= purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>