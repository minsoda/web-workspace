<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<c:choose>
	<c:when test="${vo ne null}">
	<h2>${vo.name}의 수정이 성공하셨습니다!</h2>
	<a href="/index.jsp">처음으루 가유</a>
</c:when>
	<c:otherwise>
	<h3>수정실패!</h3>
	<a href="/index.jsp">처음으루 가유</a>
	</c:otherwise>
	
</c:choose>
	
</body>
</html>