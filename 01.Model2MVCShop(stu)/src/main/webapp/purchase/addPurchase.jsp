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
	
	String payment = "신용구매";
%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%= purchaseVO.getTranNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%= userVO.getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<% if ("1".equals(purchaseVO.getPaymentOption().trim())) { 
					payment = "현금구매";
				}
		%>
		<td><%= payment %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%= purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%= purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%= purchaseVO.getDivyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%= purchaseVO.getDivyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%= purchaseVO.getDivyDate() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>