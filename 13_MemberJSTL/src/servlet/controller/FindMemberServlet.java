package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberDTO;

@WebServlet("/FindMemberServlet")
public class FindMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
//		MemberDAO dao = new MemberDAO(); // id만 받아오면 되니까 객체 생성없이 dao 연결// 싱글톤패턴
		try {
//			MemberDTO dto = dao.findByIdMember(id);// 싱글톤패턴
			MemberDTO dto = MemberDAO.getInstance().findByIdMember(id);
			
			if(dto!=null) {
				// session : 데이터 저장 상태를 무조건 유지 할때 사용 (로그인)
				// request : 상태를 유지할 필요 없을때 사용
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("views/find_ok.jsp").forward(request, response);
			} else {
				response.sendRedirect("views/find_fail.jsp");
				//  주로 값을 저장하거나 수정 후 이전페이지로 돌아갔을때 내용이 새롭게 변경될때 사용합니다.
			}
		} catch (SQLException e) {}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
