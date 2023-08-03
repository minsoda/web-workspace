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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	모델 만들었으니까 contex 일단 생성! (객체를 담아서 전달을 할 아이!)
       private ServletContext context; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로직은 여기서 작성.
		/*
		 * 1. 양방향 한글처리
		 * 2. 폼값을 받아서 
		 * 
		 * 3. vo 객체 생성 (츄가)
		 * 4. 객체 바인딩.. ServletContex에 (츄가) 
		 * 
		 * 3. ViewServlet(view)한테 결과(이름, 나이, 주소) 출력
		 * */
		// a링크로 ViewServlet 결과 확인하러 가기
		
//		 1. 양방향 한글처리. 필터서블릿을 만들었으니까 필요없지룽!
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
//		2. 폼값을 받아서
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); // int로 받아서 문자형으로 변경!
		String addr = request.getParameter("addr");
		
		
//		 3. vo 객체 생성 (츄가)
		MemberVO vo = new MemberVO(name, age, addr); // 객체 불러와이!
		
		
//		 4. 객체 바인딩.. ServletContex에 (츄가) 
		context = getServletContext(); // config에 있던 메서드(getServletContext)를 가지고 옴. 앞에 config. 생략가능!
		context.setAttribute("vovo", vo); // vovo라는 이름에 vo를 넣었다! - 객체 바인딩 작업
		
		
		
//		3. ViewServlet(view)한테 결과(이름, 나이, 주소) 출력
//		a링크로 ViewServlet 결과 확인하러 가기
		PrintWriter out = response.getWriter();
		
//		class로 modelVO로 model만들어주기!
//		out.println("<a href=view?name=" +name+ "&age=" + age + "&addr=" + addr +">ViewServlet 결과 확인하러 가기</a>");
//		out.println("<a href='view'>ViewServlet 결과 확인하러 가기");
		out.println("<a href='result.jsp'>결과 확인하러 가기");
		
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
