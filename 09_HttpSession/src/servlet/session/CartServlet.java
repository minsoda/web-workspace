package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 기본 세선을 하나 받아온다.
		HttpSession session = request.getSession();
		
		// 2. 세션에 있는 정보 출력
		PrintWriter out = response.getWriter();
		out.println("VO :: " + session.getAttribute("vo") + "<br>");
		out.println("PODUCT :: " + session.getAttribute("PRODUCT") + "<br>");

		// 3. 현재 세션에 정보가 있다면 세션을 죽인다!
		if(session.getAttribute("vo")!=null) { // 로그아웃방법. 로그인하고 링크를 타고 바로 로그아웃 메신저가 뜨고, 처음 화면!
			session.invalidate(); // 죽인당!
			out.println("<script>");
			out.println("alert('로그아웃!')");
//			out.print("location.href='index.html'"); // dom 객체 중 하나인 location
			out.print("location.href='ResultServlet'"); // 로그아웃한 이후 페이지 만듬!
			out.println("</script>"); 
		} else { // 4. 세션 정보가 없다면 처음부터 index.html
			response.sendRedirect("index.html");
		}
		// session : 로그인과 로그아웃까지 가지고 있는!
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
