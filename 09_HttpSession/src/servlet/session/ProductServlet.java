package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberVO;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context; // 리스너 context 사요하려공!
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 컨텍스트에 담아놓은 값 가져오기
		context = getServletContext(); //일단 가져오고
		String data = (String) context.getAttribute("context");
		out.println("CONTEXT :: " + data + "<br>");
		
		// 1. 기존의 세션을 받아온다.
		HttpSession session = request.getSession();
		
		// 2. 세션에 바인딩된 값이 있다면 그 값을 찾아온다.
		MemberVO vo = (MemberVO) session.getAttribute("vo"); // 키값 vo로 가져왕!. 객체로 만들었으니까 형변환!. 로그인했으면 값이 있는것 아니면 없는것
		
		if(vo != null) { // 로그인 된 상태
			out.println("VO :: " + vo);
			out.println("<hr>");
			
			session.setAttribute("PRODUCT", "NOTEBOOK"); // 장바구니 예시
			out.println("<a href=CartServlet>CartServlet..</a>");
		} else { // 로그인 실패한 상태
			out.println("session에 바인딩된 객체가 없음.. 로그인 실패!");
			out.println("<a href=index.html>로그인 페이지로 이동</a>");
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
