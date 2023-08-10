package pattern.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String path = "index.jsp";
	
		// Factory Pattern 으로 바뀐당~~
		Controller controller = ControllerFactory.getInstance().createController(command);
		try {
			// path 리턴을 받아야한다.
			// command에 따라서 작업 시작~
			// findcontroller만 추가 되고 나머지는 추가되거나 수정이 되지 않는당
			path = controller.execute(request, response);
		} catch (Exception e) {}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
