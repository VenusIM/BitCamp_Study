<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>비회원 조회</title>

<script type="text/javascript">

function fncGuestPurchase() {
	if(document.detailForm.guestPhone2.value != "" && document.detailForm.guestPhone2.value != "") {
		document.detailForm.guestPhone.value = document.detailForm.guestPhone1.value + "-" 
								+ document.detailForm.guestPhone2.value + "-" + document.detailForm.guestPhone3.value;
	} else {
		document.detailForm.guestPhone.value = "";
	}
	
	var name=document.detailForm.guestName.value;
	var phone=document.detailForm.guestPhone.value;
	
	if(name == null || name.length <1){
		alert("이름을 입력해주세요.");
		return;
	}
	if(phone == null || phone.length <1){
		alert("전화번호를 입력해주세요.");
		return;
	}
	
	document.detailForm.action='/purchase/listPurchase';
	document.detailForm.submit();
}

</script>
</head>

<body>
<div style="width: 98%; margin-left: 10px;">
<form name="detailForm" method="post">
	<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
		<tr>
			<td width="15" height="37">
				<img src="/images/ct_ttl_img01.gif" width="15" height="37">
			</td>
			<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="93%" class="ct_ttl01">비회원 조회</td>
						<td width="20%" align="right">&nbsp;</td>
					</tr>
				</table>
			</td>
			<td width="12" height="37">
				<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
			</td>
		</tr>
	</table>
		
	<input type="hidden" name="memberCheck" value="1" />
		
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">
				이름 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>				
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<input type="text" name="guestName" class="ct_input_g" style="width: 100px; height: 19px" maxLength="20" />
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">
				전화번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>				
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<select name="guestPhone1" class="ct_input_g" style="width:50px; height:25px"
					onChange="document.detailForm.guestPhone2.focus();">				<option value="010" >010</option>
					<option value="011" >011</option>	
					<option value="016" >016</option>
					<option value="018" >018</option>
					<option value="019" >019</option>
				</select>
				<input type="text" name="guestPhone2" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
					- 
				<input type="text" name="guestPhone3" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
				<input type="hidden" name="guestPhone" class="ct_input_g"  />
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
	</table>	
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
		<tr>
			<td width="53%"></td>
			<td align="right">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
						</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
								<a href="javascript:fncGuestPurchase();">검색</a>
							</td>
							
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23">
							</td>
							<td width="30"></td>					
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23">
							</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
							<a href="javascript:history.go(-1);">취소</a>
						</td>
						<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</div>
</body>

</html>