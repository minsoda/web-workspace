package servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.dao.MemberDAO;
import servlet.model.vo.MemberVO;

public class FindController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String path = "error.jsp";
		// 아이디를 입력해서 받는거라서 id!
		String id = request.getParameter("id");
		MemberVO vo = MemberDAO.getInstance().findByIdMember(id);
		
		if(vo !=null) {
			request.setAttribute("vo", vo);
			path = "find_ok.jsp";
		}
		return path;
	}

}
