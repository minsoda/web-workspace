<%@page import="servlet.model.vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

	<%-- 로그아웃할때 무조건 session 파괴하는 invalidate() 필용. 조건으로 로그아웃되었을때--%>
	<% 
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		if(dto!=null){
		session.invalidate(); 
	%>
		<body onload="return logout()">
		
		<script>
			function logout() {
				alert('LOGOUT!');
				location.href="../index.jsp";
			}
		</script>
		</body>
		<% } %>

</html>