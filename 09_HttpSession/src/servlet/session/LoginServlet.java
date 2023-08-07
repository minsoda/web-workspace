package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 세션을 하나 받아온다..request.getSession()
		// 클라이언트에게 요청을 하는 순간 무조건 만들어짐. 로그인은 객체다보니, 쿠키는 객체로 저장이 안되어서 session을 많이 사용한다.
		HttpSession session = request.getSession();
		
		// 2. 폼에 입력된 값(id, password)을 가지고 객체 생성.. MemberVO (DAO 생략)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberVO vo = new MemberVO(id, password, "김민소", "서울");
		
		// 3. 세션에 바인딩
		session.setAttribute("vo", vo); // 쿠키와 달리 객체로 넘길수있당!
		
		// 4. 네비게이션
		PrintWriter out = response.getWriter();
		out.println("<a href=ProductServlet>ProductServlet..</a>"); // 로그인후 링크로 다음으로 넘어가기 위함!
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
