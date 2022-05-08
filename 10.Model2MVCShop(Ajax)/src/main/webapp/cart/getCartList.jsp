<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	function funDeleteCart(prodNo) {
		if (confirm("선택하신 상품을 삭제하시겠습니까?")) {			
			location.href="/cart/deleteCart?userId=${user.userId}&prodNo="+prodNo;
		}
	}
	
</script>
<style>
	img {
		width: 171px;
		height: 180px;
	} 
</style>
</head>

<body>



<div class="container-fluid">
<div style="margin-bottom: 20px; margin-top: 10px; font-size: 15px;">${user.userName}님의 장바구니( ${totalCount} )</div>
<div class="row">
	<c:forEach var="cart" items="${cart}">
		
		  <div class="col-xs-6 col-md-3">
		    <div class="thumbnail">
		      <img src = "/images/uploadFiles/${cart.cartProduct.fileName}"/>
		      <div class="caption">
		        <h3>${cart.cartProduct.prodName}</h3>
		        <p>수량 : ${cart.amount}</p>
		        <p>가격 : ${cart.cartProduct.price}</p>
		        <p>
		        	<a href="/product/getProduct?menu=search&prodNo=${cart.cartProduct.prodNo}" class="btn btn-primary" role="button">상세보기</a>
		        	<a href="javascript:funDeleteCart(${cart.cartProduct.prodNo})" class="btn btn-default" role="button">삭제</a> 
		        </p>
		      </div>
		    </div>
		  
		</div>
		<%-- <table border=1>
			<tr>
				<td>상품이름</td>
				<td>${cart.cartProduct.prodName}</td>
				<td>
					<button onclick="funDeleteCart(${cart.cartProduct.prodNo})">
					X
					</button>
			</tr>
			<tr>
				<td>상품사진</td>
				<td>
					<img src = "/images/uploadFiles/${cart.cartProduct.fileName}"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>상품가격</td>
				<td>${cart.cartProduct.price}</td>
				<td></td>
			</tr>
			<tr>
				<td>상품수량</td>
				<td>${cart.amount}</td>
				<td></td>
			</tr>
		</table> --%>
	</c:forEach>
	</div>
</div>
</body>
</html>