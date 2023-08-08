package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberDTO;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 폼값을 가져온다. (DAO에게 넘길 값을 가져와야한당)
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		// 2. 객체 생성 - 폼 값 담기
		MemberDTO dto = new MemberDTO();
		
		// dto에 생성자가 없기 떄문에 set으로 송송!
		dto.setId(id);
		dto.setPassword(password);
		dto.setName(name);
		dto.setAddress(address);
		
		// 3. DAO와 연결
//		MemberDAO dao = new MemberDAO();// 싱글톤패턴
		try {
//			dao.registerMember(dto);// 싱글톤패턴
			MemberDAO.getInstance().registerMember(dto);
			
		// 4. 데이터 바인딩 - session에 데이터 바인딩 : 회원가입과 함께 넘긴다는 뜻
			HttpSession session = request.getSession();
			session.setAttribute("dto", dto);
			
			// 5. 네비게이션
			response.sendRedirect("AllMemberServlet");
			
		} catch (SQLException e) {
			
		System.out.println("회원가입실패"); // pk으로 설정이 되기 때문에 if 사용하지않아도 된당!
		response.sendRedirect("../index.jsp");
		
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
