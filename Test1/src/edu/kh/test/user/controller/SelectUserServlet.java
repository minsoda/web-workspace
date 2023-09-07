package edu.kh.test.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.UserDAO;
import edu.kh.test.user.model.UserDTO;


@WebServlet("/Test1")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//폼값 받아오고 dao 리턴하고 바인딩하고 보내기!
		
		int id = InterParese(request.getParameter("id"));
		
		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		
		try {
			dto = dao.search(id);
			request.setAttribute("dto", dto);
			
		} catch (SQLException e) {}
		
		if(dto.equals(id)) {
			request.getRequestDispatcher("searchSuccess.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("searchFail.jsp").forward(request, response);
	}

	private int InterParese(String parameter) {
		return 0;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
