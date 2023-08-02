package servlet.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfigServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name, addr;
	
	public void init(ServletConfig config) throws ServletException {
		name = config.getInitParameter("name");
		addr = config.getInitParameter("addr");
		System.out.println(name + ", " + addr);
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h3>이름 :  " + name + "</h3>");
		out.println("<h3>주소 :  " + addr + "</h3>");
		
//		웹.xml이 먼저 실행되기때문에 config 수정 가능
		/* <init-param>
    	<param-name>name</param-name>
    	<param-value>김유저</param-value>
    </init-param> 
    */
		out.close();
	}

}
