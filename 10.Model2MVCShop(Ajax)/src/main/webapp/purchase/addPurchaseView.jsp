<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>

<script type="text/javascript">

	function fncAddPurchase() {
		
		/* if(document.addPurchase.phone2.value != "" && document.addPurchase.phone3.value != "") {
			document.addPurchase.receiverPhone.value = document.addPurchase.phone1.value + "-" + document.addPurchase.phone2.value + "-" + document.addPurchase.phone3.value;
		} else {
			document.addPurchase.receiverPhone.value = "";
		} */
		
		alert($("select[name='phone1']").val());
		
		if($("input[name='phone2']").val() != "" && $("input[name='phone3']").val() != "") {
			$("input[name='receiverPhone']").val($("select[name='phone1']").val() + "-" + $("input[name='phone2']").val() + "-" + $("input[name='phone3']").val());
		} else {
			//document.addPurchase.receiverPhone.value = "";
			$("input[name='receiverPhone']").val("");
		}
		
		if (${empty user}) {
			//var name=document.addPurchase.receiverName.value;
			//var phone=document.addPurchase.receiverPhone.value;
			//var addr=document.addPurchase.divyAddr.value;
			
			var name = $("input[name='receiverName']").val();
			var phone = $("input[name='receiverPhone']").val();
			var addr = $("input[name='divyAddr']").val();
			
			if(name == null || name.length <1){
				alert("������ �̸��� �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
			if(phone == null || phone.length <1){
				alert("������ ��ȭ��ȣ��  �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
			if(addr == null || addr.length <1){
				alert("������ �ּҴ�  �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
		}
		
		//document.addPurchase.submit();
		//$("form").attr("method", "POST").attr("action", "/purchase/addPurchase").submit();
	}
	
	$(function() {
		$($(".ct_btn01")[0]).click(function() {
			fncAddPurchase();
		});
		
		$($(".ct_btn01")[1]).click(function() {
			window.history.back();
		});
		
		$("select[name='phone1']").change(function(){
			$("input[name='phone2']").focus();
		});
		
		$("input[name='receiverDate']").datepicker({
			showOn:"both",
			buttonImage:"../images/ct_icon_date.gif",
			buttonImageOnly:true,
			changeMonth: true,
			changeYear: true,
			dateFormat:"yy-mm-dd"
		});
	});
</script>
</head>

<body>
<div style="width: 98%; margin-left: 10px;">
<!-- <form name="addPurchase" method="post" action="/purchase/addPurchase"> -->
<form>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ǰ����</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<input type="hidden" name="prodNo" value="${product.prodNo}" />

<table width="100%" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="300" class="ct_write">
			��ǰ��ȣ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01" width="299">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${product.prodNo}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			��ǰ�� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.prodName}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			��ǰ������ <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.prodDetail}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">��������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.manuDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.price}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${product.regDate}</td>
	</tr>
	<c:choose>
		<c:when test="${!empty user}">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					�����ھ��̵� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${user.userId}</td>
				<input type="hidden" name="buyerId" value="${user.userId}" />
				<input type="hidden" name="memberCheck" value="0" />
			</tr>
		</c:when>
		<c:otherwise>
			<input type="hidden" name="memberCheck" value="1" />
		</c:otherwise>
	</c:choose>
	
	
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			���Ź��<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select name="paymentOption" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20">
				<option value="1" selected="selected">���ݱ���</option>
				<option value="2">�ſ뱸��</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			�������̸�
			<c:if test="${empty user}">
				<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>				
			</c:if> 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="receiverName" 	class="ct_input_g" 
					style="width: 100px; height: 19px" maxLength="20" value="${user.userName}" />

		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			�����ڿ���ó
			<c:if test="${empty user}">
				<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>				
			</c:if> 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<!-- <select name="phone1" class="ct_input_g" style="width:50px; height:25px"
							onChange="document.detailForm.phone2.focus();"> -->
			<select name="phone1" class="ct_input_g" style="width:50px; height:25px">
				<option value="010" ${ ! empty user.phone1 && user.phone1 == "010" ? "selected" : ""  } >010</option>
				<option value="011" ${ ! empty user.phone1 && user.phone1 == "011" ? "selected" : ""  } >011</option>
				<option value="016" ${ ! empty user.phone1 && user.phone1 == "016" ? "selected" : ""  } >016</option>
				<option value="018" ${ ! empty user.phone1 && user.phone1 == "018" ? "selected" : ""  } >018</option>
				<option value="019" ${ ! empty user.phone1 && user.phone1 == "019" ? "selected" : ""  } >019</option>
			</select>
			<input 	type="text" name="phone2" value="${!empty user.phone2 ? user.phone2 : ''}" 
			 		class="ct_input_g" style="width:100px; height:19px"  maxLength="9" />
			- 
			<input 	type="text" name="phone3" value="${!empty user.phone3 ? user.phone3 : ''}"  
					class="ct_input_g" style="width:100px; height:19px"  maxLength="9" />
			<input 	type="hidden" name="receiverPhone" class="ct_input_g" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			�������ּ�
			<c:if test="${empty user}">
				<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>				
			</c:if> 
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="divyAddr" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" 	value="${user.addr}" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���ſ�û����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="divyRequest" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">����������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
			<input 	type="text" readonly="readonly" name="receiverDate" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="20"/>
			<!-- <img 	src="../images/ct_icon_date.gif" width="15" height="15"	
						onclick="show_calendar('document.addPurchase.receiverDate', document.addPurchase.receiverDate.value)"/> -->
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						<!-- <a href="javascript:fncAddPurchase();">����</a> -->
						����
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
						<!-- <a href="javascript:history.go(-1)">���</a> -->
						���
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
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