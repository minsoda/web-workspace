<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 정보 수정</h2>
	
	<form action="/front.do" method="post">
	<input type="hidden" name="command" value="update">
	아이디(변경불가) : <input type="text" name="id" value="${vo.id}" readonly><br>
	변경할 비밀번호 : <input type="password" name="password" value="${vo.password}"><br>
	변경할 이름 : <input type="text" name="name" value="${vo.name}"><br>
	변경할 주소 : <input type="text" name="address" value="${vo.address}"><br>
	<input type="submit" value="전송하기">

	</form>
	
	
</body>
</html>