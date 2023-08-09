package servlet.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberVO;

//servlet하고 jsp로 넘어가!. /front.do로 되니까, views/ 로 경로 지정해줘야함!

@WebServlet(urlPatterns = "/front.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청이 어디에서 들어오는 요청인지 command 값 받는다.
		String command = request.getParameter("command");
		String path = "index.jsp";

			try {
				if(command.equals("register")) {
					path = register(request, response);
					
				} else if(command.equals("login")) {
					path = login(request, response);
					
				} else if(command.equals("showAllMember")) {
					path = showAllMember(request, response);
					
				} else if(command.equals("update")) {
					path = update(request, response);
					
				} else if (command.equals("search")) {
					path = search(request, response);
					
				} else if (command.equals("logout")) {
					path = logout(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// 네비게이션(공용)
		request.getRequestDispatcher(path).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	// 위에 조건에 사용할 register 함수 필요해서 만듬!
	protected String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//1. 폼값을 가져온다.
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		MemberVO vo = new MemberVO(id, password, name, address);
		
		MemberDAO.getInstance().registerMember(vo);
		
		
		return "index.jsp";
	}
	
	protected String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// return 한게 membervo 니까 담아유~
		MemberVO vo = MemberDAO.getInstance().login(id,password);
		
		// 로그인했으니까 session
		HttpSession session = request.getSession();
		
		// 한번더 체크해주기 위해서 if 조건 걸어!
		if(vo!=null) {
			session.setAttribute("vo", vo);
		}
			return "views/login_result.jsp";
	
	}
	
	protected String showAllMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		ArrayList<MemberVO> list = MemberDAO.getInstance().showAllMember();
		request.setAttribute("list", list);
		
		
		return "views/allShow.jsp";

	}
	
	protected String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		MemberVO vo = new MemberVO(id, password, name, address);
		
		MemberDAO.getInstance().updateMember(vo);
		
		HttpSession session = request.getSession();
		
		// 항상 체크해주기
		if(session.getAttribute("vo")!=null) {
		session.setAttribute("vo", vo);
		return "views/update_result.jsp";
		}
		return "inex.jsp";
	}
	
	protected String search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String id = request.getParameter("id");
		
		MemberVO vo = MemberDAO.getInstance().findByIdMember(id);
		
		if(vo == null) {	
			return "views/find_fail.jsp";
		} 
		request.setAttribute("vo", vo);
		return "views/find_ok.jsp";
		
	}
	
	protected String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//invalidate()만 해주면 되서 session!
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("vo")!=null) {
			session.invalidate();
			return "views/logout.jsp";
		}
		
		return "index.jsp";
	}
	
	
	}
