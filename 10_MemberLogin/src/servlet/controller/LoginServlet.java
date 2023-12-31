package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 폼값 받음
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 2. 객체 생성 - 필요없음 (생략)
		
		try {
			// 3. DAO 연결
//			MemberDAO dao = new MemberDAO();// 싱글톤패턴
//			MemberDTO dto = dao.login(id, password);// 싱글톤패턴
			MemberDTO dto = MemberDAO.getInstance().login(id, password);
			
			// 4. 데이터 바인딩 - session
			HttpSession session = request.getSession();
			session.setAttribute("dto", dto);
			
			// 5. 네비게이션
			response.sendRedirect("views/login_result.jsp");
			
		} catch (SQLException e) {
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}