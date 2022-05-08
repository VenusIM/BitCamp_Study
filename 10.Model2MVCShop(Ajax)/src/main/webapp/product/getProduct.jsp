<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	function funGuest() {
		if (confirm("비회원입니다. 구매하시겠습니까?")) {
			/* location.href="/purchase/addPurchase?prod_no="+${product.prodNo}; */
			self.location="/purchase/addPurchase?prod_no=${product.prodNo}";
		}
	}

	function funCart() {
		if (confirm("장바구니에 상품을 담았습니다.\n\n장바구니 보러가기")) {
			location.href="/cart/addCart/ok";
		} else {
			location.href="/cart/addCart/cancel";
		}
	}
	
	$(function() {
		if (${param.menu != "ok"}) {
			$($(".ct_btn01")[2]).text("이전");
		} 
		
		$(".ct_btn01:contains('이전')").click(function(){
			window.history.back();
		});
		
		$(".ct_btn01:contains('확인')").click(function(){
			self.location="/product/listProduct?menu=manage";
		});
		
		$(".ct_btn01:contains('구매')").click(function(){
			if (${!empty user}) {
				self.location="/purchase/addPurchase?prod_no=${product.prodNo}";				
			} else {
				funGuest();
			}
		});
		
		$("form").attr("method", "POST");
	});
</script>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<title>Insert title here</title>
</head>

<body bgcolor="#ffffff" text="#000000">
<div style="width: 98%; margin-left: 10px;">
<!-- <form name="detailForm" method="post"> -->
<form>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"	width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품상세조회</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"  width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${sessionScope.product.prodNo}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%-- <td class="ct_write01"><%= product.getProdName() %></td> --%>
		<td class="ct_write01">${sessionScope.product.prodName}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품이미지 <img 	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<img src = "/images/uploadFiles/${sessionScope.product.fileName}"/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%-- <td class="ct_write01"><%= product.getProdDetail() %></td> --%>
		<td class="ct_write01">${sessionScope.product.prodDetail}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">제조일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%-- <td class="ct_write01"><%= product.getManuDate() %></td> --%>
		<td class="ct_write01">${sessionScope.product.manuDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">가격</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%-- <td class="ct_write01"><%= product.getPrice() %></td> --%>
		<td class="ct_write01">${sessionScope.product.price}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">등록일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<%-- <td class="ct_write01"><%= product.getRegDate() %></td> --%>
		<td class="ct_write01">${sessionScope.product.regDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">

		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<c:if test="${param.menu != 'ok'}">
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">	
						<a href="javascript:funCart()">담기</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
					<td width="30"></td>
					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">	
					<%-- <c:choose>
						<c:when test="${!empty user}">
							<a href="/purchase/addPurchase?prod_no=${product.prodNo}">구매</a>								
						</c:when>
						<c:otherwise>
							<a href="javascript:funGuest()">구매</a>
						</c:otherwise>
					</c:choose> --%>
						구매
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
					<td width="30"></td>
				</c:if>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						<%-- <c:choose>
						    <c:when test="${param.menu != 'ok'}">
						        <!-- <a href="javascript:history.go(-1)">이전</a> -->
						        이전
						    </c:when>
						    <c:otherwise>
						        <!-- <a href="/product/listProduct?menu=manage">확인</a> -->
						        확인
						    </c:otherwise>
						</c:choose> --%>
						확인
					</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>