<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>�������� ����</title>

<script type="text/javascript" src="../javascript/calendar.js">
</script>

<script type="text/javascript">

function fncUpdatePurchase() {
	if(document.updatePurchase.phone2.value != "" && document.updatePurchase.phone3.value != "") {
		document.updatePurchase.receiverPhone.value = document.updatePurchase.phone1.value + "-" + document.updatePurchase.phone2.value + "-" + document.updatePurchase.phone3.value;
	} else {
		document.updatePurchase.receiverPhone.value = "";
	}
	
	document.updatePurchase.submit();
}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">
<div style="width: 98%; margin-left: 10px;">
<form name="updatePurchase" method="post"	action="/purchase/updatePurchase?tranNo=${purchase.tranNo}">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif"  width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">������������</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<c:if test="${!empty user}">
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">�����ھ��̵�</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">${purchase.buyer.userId}</td>
			<input type="hidden" name="buyerId" value="${purchase.buyer.userId}">
		</tr>
	</c:if>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���Ź��</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20">
				<option value="1" selected=${purchase.paymentOption == '1' ? 'selected' : ''}>���ݱ���</option>
				<option value="2" selected=${purchase.paymentOption == '2' ? 'selected' : ''}>�ſ뱸��</option>
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������̸�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverName" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchase.receiverName}" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">������ ����ó</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="phone1" class="ct_input_g" style="width:50px; height:25px"
							onChange="document.detailForm.phone2.focus();">
				<option value="010" ${ ! empty purchase.phone1 && purchase.phone1 == "010" ? "selected" : ""  } >010</option>
				<option value="011" ${ ! empty purchase.phone1 && purchase.phone1 == "011" ? "selected" : ""  } >011</option>
				<option value="016" ${ ! empty purchase.phone1 && purchase.phone1 == "016" ? "selected" : ""  } >016</option>
				<option value="018" ${ ! empty purchase.phone1 && purchase.phone1 == "018" ? "selected" : ""  } >018</option>
				<option value="019" ${ ! empty purchase.phone1 && purchase.phone1 == "019" ? "selected" : ""  } >019</option>
			</select>
			<input 	type="text" name="phone2" value="${ ! empty purchase.phone2 ? purchase.phone2 : ''}" 
			 		class="ct_input_g" style="width:100px; height:19px"  maxLength="9" />
			- 
			<input 	type="text" name="phone3" value="${ ! empty purchase.phone3 ? purchase.phone3 : ''}"  
					class="ct_input_g" style="width:100px; height:19px"  maxLength="9" />
			<input 	type="hidden" name="receiverPhone" class="ct_input_g" />
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������ּ�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="divyAddr" class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchase.divyAddr}" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���ſ�û����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="divyRequest" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchase.divyRequest}" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">����������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
			<input type="text" readonly="readonly" name="divyDate" class="ct_input_g" 
						style="width: 100px; height: 19px" maxLength="20"/>
				<img src="../images/ct_icon_date.gif" width="15" height="15"	
							onclick="show_calendar('document.updatePurchase.divyDate', document.updatePurchase.divyDate.value)"/>
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
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
					<a href="javascript:fncUpdatePurchase();">����</a>
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
				<td width="30"></td>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<a href="javascript:history.go(-1)">���</a>
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