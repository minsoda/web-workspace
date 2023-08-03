package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberVO;

/**
 * Servlet implementation class ViewServlet
 */
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context; // 작성해주고! 1,2 작성
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		필터서블릿을 만들었으니까 필요없지룽!
		//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
//		// 출력하는 값 받아오기.
//		String name = request.getParameter("name");
//		int age = Integer.parseInt(request.getParameter("age")); // int로 받아서 문자형으로 변경!
//		String addr = request.getParameter("addr");
		
		/*
		 * 1. Attribute에 바인딩된 데이터를 받아와서 
		 * 2. 웹브라우저로 출력하는 로직을 작성..
		 * */
		
//		1. Attribute에 바인딩된 데이터를 받아와서 
		context = getServletContext();
		
		// registerServlet에서 가져오는거니까 get, vovo로 지정을 했기때문에 "vovo"담고 멤버를 가져왔기때문에 형변환(membervo) 해주공~
//		getAttribute은return값이 object기에 형변환!
		MemberVO vo = (MemberVO) context.getAttribute("vovo"); 

//		2. 웹브라우저로 출력하는 로직을 작성..
		PrintWriter out = response.getWriter();
		out.println("<h2>회원님의 정보를 출력합니다.</h2>");
		out.println("<p> 이름 : " + vo.getName() + "</p>");
		out.println("<p> 나이 : " + vo.getAge() + "</p>");
		out.println("<p> 주소 : " + vo.getAddr() + "</p>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	


}
