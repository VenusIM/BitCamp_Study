<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>

<html>
<style>
.ui-autocomplete { 
    overflow-y: scroll; 
    overflow-x: hidden;}
</STYLE>

<head>

<c:choose>
	<c:when test="${param.menu == 'manage'}">
		<title>상품 관리</title>
	</c:when>
	<c:otherwise>
		<title>상품 목록조회</title>
	</c:otherwise>
</c:choose>



<script type="text/javascript">

   function searchEnterkey() {
	  if (window.event.keyCode == 13) {
		  fncGetProductList(1);
      }
   }

   function fncGetProductList(currentPage) {
	  /* document.getElementById("currentPage").value = currentPage;
      document.detailForm.submit(); */
      
      $("#currentPage").val(currentPage);
      $("form").attr("action","/product/listProduct?menu=${param.menu}").attr("method", "POST").submit();
   }
   
   $(function() {
	   $("input[name='searchKeyword']").keyup(function() {
		   searchEnterkey();
	   });
	   
	   $(".ct_btn01").click(function() {
		   fncGetProductList('1');
	   });
	   
	   $("#orderName").click(function(){
		   self.location="/product/listProduct?menu=${param.menu}&searchOrder=1";
	   });
	   
	   $("#orderPrice1").click(function(){
		   self.location="/product/listProduct?menu=${param.menu}&searchOrder=2";
	   });
	   
	   $("#orderPrice2").click(function(){
		   self.location="/product/listProduct?menu=${param.menu}&searchOrder=3";
	   });
	   
	   $(".ct_list_pop td:nth-child(3)").click(function(){
		   var prodNo = $(this).find("#h_prodNo").val();
		   var tranCode = $(this).find("#h_tranCode").val();
		   self.location="/product/getProduct?prodNo="+prodNo+"&menu=${param.menu}&tranCode="+tranCode; 
	   });
	   
	   $("#div").click(function(){
		   alert($("#div").val()); 
		   //self.location="/purchase/updateTranCodeByProd?prodNo="+$("#div").val()+"&tranCode=2"
	   });
	   
	   $("input[name='searchKeyword']").autocomplete({
	     source: function(request, response) {
	    	 		$.ajax({
	    	 			url:"json/getProductKeyword",
		    	  		method : "GET",
						dataType : "json",
						contentType: 'application/x-www-form-urlencoded; charset=MS949',
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						data : {
							"keyWord" : encodeURI($("input[name='searchKeyword']").val())
						},
						success : function(data, status) {
							//alert(status);
							//alert( "JSON.stringify(JSONData) : \n"+JSON.stringify(data) );
							
							//alert(data);
							response(data);   //response
						}
		      		});
	     		}
	   });
	});
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

   <div style="width: 98%; margin-left: 10px;">

      <%-- <form name="detailForm" action="/product/listProduct?menu=${param.menu}" method="post"> --%>
      <form>

         <table width="100%" height="37" border="0" cellpadding="0"
            cellspacing="0">
            <tr>
               <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
                  width="15" height="37" /></td>
               <td background="/images/ct_ttl_img02.gif" width="100%"
                  style="padding-left: 10px;">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                     <tr>
                     	<c:choose>
                     		<c:when test="${param.menu == 'manage'}">
	                     		<td width="93%" class="ct_ttl01">상품 관리</td>                     		
                     		</c:when>
                     		<c:otherwise>
                     			<td width="93%" class="ct_ttl01">상품 목록조회</td>
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
	                  <c:if test="${!empty user && user.userId == 'admin'}">
	                    <option value="0" ${search.searchCondition == '0' ? 'selected' : ''}>상품번호</option>             
	                  </c:if>
	                    <option value="1" ${search.searchCondition == '1' ? 'selected' : ''}>상품명</option>
	               		<option value="2" ${search.searchCondition == '2' ? 'selected' : ''}>상품가격</option>
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
                           	<!-- <a href="javascript:fncGetProductList('1');">검색</a> -->
                           	검색
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
               		전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지 
               </td>
               <c:if test="${param.menu != 'manage'}">
	               <td align="right">
	               		 <input type="hidden" id="searchOrder" name="searchOrder" value="${searchOrder}"/>
			 			<%-- <a href="/product/listProduct?menu=${param.menu}&searchOrder=1">상품명</a> | <a href="/product/listProduct?menu=${param.menu}&searchOrder=2">낮은가격</a> | <a href="/product/listProduct?menu=${param.menu}&searchOrder=3">높은가격</a> --%>
			 			<span id="orderName">상품명</span> | 
			 			<span id="orderPrice1">낮은가격</span> | 
			 			<span id="orderPrice2">높은가격</span>
	               </td>               
               </c:if>
            </tr>
            <tr>
               <td class="ct_list_b" width="100">No</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">상품명</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b" width="150">가격</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">등록일</td>
               <td class="ct_line02"></td>
               <td class="ct_list_b">현재상태</td>
            </tr>
            <tr>
               <td colspan="11" bgcolor="808285" height="1"></td>
            </tr>

            <c:set var="i" value="0" />
            <c:forEach var="product" items= "${list}">
            	<c:set var="i" value="${i+1}"/>
				<tr class="ct_list_pop">
	               <td align="center">${i}</td>
	               <td></td>
	               <td align="left">
	               	    <input type="hidden" id="h_prodNo" value="${product.prodNo}"/>
		                <input type="hidden" id="h_tranCode" value="${product.proTranCode}"/>
	               	  	${product.prodName}
	               </td>
	               <td></td>
	               <td align="left">${product.price}</td>
	               <td></td>
	               <td align="left">${product.regDate}</td>
	               <td></td>
	               <c:choose>
	               		<c:when test="${empty product.proTranCode}">
	               			<td align="left">판매중</td>
	               		</c:when>
	               		<c:otherwise>
	               			<c:choose>
	               				<c:when test="${user.role == 'admin'}">
	               					<c:if test="${product.proTranCode == '1'}">
	               						<c:choose>
	               							<c:when test="${param.menu == 'manage'}">
	               								<td align="left">구매완료
	               									<button id="div" value="${product.prodNo}">배송하기</button>
	               								</td>
	               							</c:when>
	               							<c:otherwise>
	               								<td align="left">구매완료</td>
	               							</c:otherwise>
	               						</c:choose>
				               		</c:if>
				               		<c:if test="${product.proTranCode == '2'}">
				               			<td align="left">배송중</td>
				               		</c:if>
				               		<c:if test="${product.proTranCode == '3'}">
				               			<td align="left">배송완료</td>
				               		</c:if>
	               				</c:when>
	               				<c:otherwise>
	               					<td align="left">재고없음</td>
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
              
				 <jsp:include page="../common/pageNavigator.jsp">
				 	<jsp:param value="fncGetProductList" name="page"/>
				 </jsp:include>
               </td>
            </tr>
         </table>
         <!--  페이지 Navigator 끝 -->
      </form>

   </div>
</body>
</html>