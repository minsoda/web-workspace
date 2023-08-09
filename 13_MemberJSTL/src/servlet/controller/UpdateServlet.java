package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberDTO;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 폼 값을 받는다.
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		// 2. 객체 생성
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		System.out.println(id);
		dto.setPassword(password);
		System.out.println(password);
		dto.setName(name);
		System.out.println(name);
		dto.setAddress(address);
		System.out.println(address);
		
		try {
			// 3. DAO 
			MemberDAO.getInstance().updateMember(dto);
			
			// 4. 데이터 바인딩
			HttpSession session = request.getSession();
			if(session.getAttribute("dto")!=null) {
				session.setAttribute("dto", dto);
			}
			
			// 5. 네비게이션. 바인딩해서 보낼때 forward사용.
			request.getRequestDispatcher("views/update_result.jsp").forward(request, response);
			
			// session으로 담았기 때문에 아래의 방법으로도 가능!
//			response.sendRedirect("views/update_result.jsp");
			
		} catch (SQLException e) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
