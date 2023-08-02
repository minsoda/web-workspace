package servlet.header;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PrintHeaderServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		PrintWriter out = response.getWriter();
		out.println("<h2>여기는 doGet입니다..</h2>");
		
		// header 정보
		Enumeration<String> en = request.getHeaderNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement(); // key를 하나씩 뺴온당
			String value = request.getHeader(key);
			out.println("<p>key : " + key + " , value : " + value + "</p>");
		}
		out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // doget을 작성하고 호출해서 사용을 한다!
	}

}
