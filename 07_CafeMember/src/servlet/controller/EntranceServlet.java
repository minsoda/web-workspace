package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberVO;

/**
 * Servlet implementation class EntranceServlet
 */
public class EntranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	List<MemberVO> list = Collections.synchronizedList(new ArrayList<MemberVO>());

	/*
	 * 회원가입하면.. 정보를 바탕으로
	 * MemberVO를 생성..ArrayList에 추가(한번만 들어가니, init() 필요)
	 * ArrayList를 통째로 ServletContext에 바인딩 가능
	 * 출력결과는 viewMember.jsp 페이지에서 
	 * */
	
	public void init(ServletConfig config) throws ServletException {
		
//		주소 상관없이 들어갔을때 전부 가능
//		context = config.getServletContext(); //config 가지고 있어서 적어줘야함!
//		context.setAttribute("list", list);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		접속했을때 list 보기
		request.setAttribute("list", list);
		
		String name = request.getParameter("name");
		int age = request.getParameter("age")!=null ? Integer.parseInt(request.getParameter("age")) : 0; // 회원가입없이 들어가기 위해서 null 조건 처리. jsp에서도 처리
		String addr = request.getParameter("addr");
		System.out.println("1. 폼값을 받아온다.");
		
		
		if(name!=null) { // 회원가입없이 들어가기 위해서 null 조건 처리. jsp에서도 처리
		MemberVO vo = new MemberVO(name, age, addr);
		System.out.println("2. MemberVO 생성..");
		
		list.add(vo);
		System.out.println("3. MemberVO를 List에 저장..");
		}
		
		// 내비게이션(다른페이지로 넘어가는것). 출력할 이동할곳 적어주기!. 바로 jsp로 이동
		RequestDispatcher rdp = request.getRequestDispatcher("viewMember.jsp"); // 이동할 곳 설정.
		rdp.forward(request, response); // 이때 위에 설정한 페이지에 이동!
		
		
//		PrintWriter out = response.getWriter(); // 링크타고 이동.
//		out.println("<a href='viewMember.jsp'>결과 확인하러 가기");
//		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
