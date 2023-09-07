package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberDAO;
import servlet.model.MemberVO;


public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 폼 데이터 받아오기
		// 2. DAO 리턴받아서
		// 3. 멤버 정보 1개 바인딩
		// 4. 네비게이션 -> view.jsp
		
			// 1. 폼 데이터 받아오기
			String name = request.getParameter("name");
		
			// 2. DAO 리턴받아서
			MemberDAO dao = new MemberDAO();
			MemberVO vo = null; // try catch 때문에!
		
			try {
				vo = dao.findByNameMember(name);
			} catch (SQLException e) {}
		
			
			// 3. 멤버 정보 1개 바인딩
			request.setAttribute("vo", vo);
			
			// 4. 네비게이션 -> view.jsp
			RequestDispatcher rdp = request.getRequestDispatcher("view.jsp");
			rdp.forward(request, response);
			
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
