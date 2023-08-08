<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<c:choose>
	<c:when test="${dto ne null}">
	<h1>로그인 정보</h1>
	<ul>
		<li>아이디(변경불가) : ${dto.id}</li><br>
		<li>변경된 비밀번호 : ${dto.password}</li>
		<li>변경된 이름 : ${dto.name}</li>
		<li>변경된 주소 : ${dto.address}</li>
		</ul>
	<h2>${dto.name}의 수정이 성공하셨습니다!</h2>
	
</c:when>
	<c:otherwise>
	<h3>수정실패!</h3>
	<a href="/index.jsp">처음으루 가유</a>
	</c:otherwise>
	
</c:choose>
	
</body>
</html>