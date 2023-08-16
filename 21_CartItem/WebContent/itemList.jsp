<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <style>
    	div {
    	display: flex;
        justify-content: center;
        align-items: center;
    	}
    	img {
    	width: 60px;
        height: 60px;
        margin: 10px;
    	}
    	h1 {
    	text-align: center;
    	}
    </style>
  </head>
  <body>
    <h1>Fruit Total List</h1>
    
    <c:forEach items="${list}" var="item">
			<!-- item 변수명! -->
			<div>
			<a href="itemView.do"><img src="${item.pictureUrl}"></a>
			${item.itemName}
			${item.price}
			</div>
    console.log(${list});
	</c:forEach>
    <h1>오늘 본 상품들</h1>
    
   
  </body>
</html>
