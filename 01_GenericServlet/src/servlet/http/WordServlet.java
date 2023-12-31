package servlet.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// index.html에서 name=word(key값)설정 후 web.xml에서 <url-pattern>/word</url-pattern>로 변경하면
	// 단어 입력 시 word 페이지로 넘어가서 Client Send Data...가 출력됨
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 폼값 받아서 
		 * 2. 다시 웹브라우저로 전송(출력)
		 * */
		// request로 들어온 데이터를 result로 담아서 out.println으로 출력함
		String result = request.getParameter("word"); // word는 index.html에서 form의 name값
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>Client Send Data...</h2>");
		out.println("<p>" + result + "</p>");
		out.println("</body></html>");
	}

}
