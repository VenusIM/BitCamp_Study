<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	당신이 열어본 상품을 알고 있다
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	String history = null;
	Cookie[] cookies = request.getCookies();
	System.out.println("cookie 확인 값");
	if (cookies!=null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			System.out.println("cookie 있음");
			if (cookie.getName().equals("history")) {
				history = cookie.getValue();
				System.out.println("cookie 확인 값... ::  "+history);
			}
		}
		if (history != null) {
			String[] h = history.split(",");
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) {
%>
<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a>
<br>
<%
				}
			}
		}
	}
%>

</body>
</html>