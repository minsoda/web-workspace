<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%= import edu.kh.test.user.model.UserDTO %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		UserDTO dto = new UserDTO();
		request.getAttribute(dto);
	%>
	<h2>회원정보</h2>
	<table border="1">
		<thead>
			<th>회원번호</th>
			<th>회원아이디</th>
			<th>회원이름</th>
			<th>회원나이</th>
		</thead>
		
		<tbody>
			<tr>
				<td>${dto.get(userNo)}</td>
				<td>${dto.get(userId)}</td>
				<td>${dto.get(userName)}</td>
				<td>${dto.get(userAge)}</td>
			</tr>
		</tbody>
	
	</table>
	
	<a href="index.jsp">메인페이지로 돌아가기</a>
</body>
</html>