package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberDAO;
import servlet.model.MemberVO;


public class VewMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext context;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. DAO 리턴 받기
		// 2. 바인딩
		// 3. 네비게이션 --> viewMember.jsp
		
		// 1. DAO 리턴 받기
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = null;
		
		try {
			list = dao.showAllMember();
		} catch (SQLException e) {}
		
		// 2. 바인딩
		request.setAttribute("list", list); // 바인딩
		
		// 3. 네비게이션 --> viewMember.jsp
//		RequestDispatcher rdp = request.getRequestDispatcher("viewMember.jsp");
//		rdp.forward(request, response);
		
		// 이렇게 해도 가능!
		request.getRequestDispatcher("viewMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
