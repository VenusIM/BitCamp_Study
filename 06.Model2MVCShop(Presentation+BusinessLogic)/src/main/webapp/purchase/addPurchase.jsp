<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%
	Purchase purchase = (Purchase)request.getAttribute("purchase");
	System.out.println(getClass().getName() + " :: purchase = "+ purchase);
	
	User user = purchase.getBuyer();
	System.out.println(getClass().getName() + " :: user = "+ user);
	
	Product product = purchase.getPurchaseProd();
	System.out.println(getClass().getName() + " :: productVO = "+ product);
	
	String payment = "�ſ뱸��";
%>
--%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${product.prodNo}</td>
		<td></td>
	</tr>
	<c:if test="${!empty user}">
	<tr>
		<td>�����ھ��̵�</td>
		<td>${user.userId}</td>
		<td></td>
	</tr>
	</c:if>
	<tr>
		<td>���Ź��</td>
		<c:choose>
			<c:when test="${purchase.paymentOption == 1}">
				<td>���ݱ���</td>
			</c:when>
			<c:otherwise>
				<td>�ſ뱸��</td>			
			</c:otherwise>
		</c:choose>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>

</body>
</html>