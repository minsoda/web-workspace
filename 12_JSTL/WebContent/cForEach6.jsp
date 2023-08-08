<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String[] arr = {"삼계탕", "돈까스", "삼겹살", "설렁탕", "제육볶음", "감자전"};
		request.setAttribute("arr", arr);
	%>
	
	<jsp:forward page="cForEach6_vies.jsp"/>
</body>
</html>