
<%--
페이지소스에서 보기로 볼때 이 주석은 보이지않음!

JSP Element
1) 지시어 %@ % : 컨테이너에게 알려줄 내용 지정
2) 스클릿틀릿 % % : 자바코드는 이 안에 지정
3) 출력문 %= % : 출력하려는 내용 지정
 --%>
 <%@ page import="servlet.model.MemberVO" %>
 <%-- 멤버객체를 불러오기 위해 import 필요. 지시어에 해당되는 %@ 작성! --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- % % :: Scriptlet Element 
	context에 담아놓은걸 여기서 쓰기 위해서는 application으로 가져와야하고 역시 형변환!해야한다!
-->
<% 
	MemberVO vo = (MemberVO) application.getAttribute("vovo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- %= % :: Expression Element 
		out.println("<h2>회원님의 정보를 출력합니다.</h2>");
		out.println("<p> 이름 : " + vo.getName() + "</p>");
		out.println("<p> 나이 : " + vo.getAge() + "</p>");
		out.println("<p> 주소 : " + vo.getAddr() + "</p>");
	-->
	
	<h2>회원님의 정보를 출력합니다.</h2>
	<p> 이름 : <%= vo.getName() %></p>
	<p> 나이 : <%= vo.getAge() %></p>
	<p> 주소 : <%= vo.getAddr() %></p>
	
</body>
</html>