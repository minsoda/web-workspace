package member.controller.component;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.controller.Controller;
import member.controller.ModelAndView;
import member.model.service.MemberService;
import member.model.vo.MemberVO;

public class FindController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String path = "views/find_fail.jsp";
		
		String id = request.getParameter("id");
		String addr = request.getParameter("addr");
		
		// 체크된 아이디를 전달!
		String[] idList = request.getParameterValues("checkId");
		
		MemberVO vo = new MemberVO();
		if(id != "") vo.setId(id);
		if(addr != "") vo.setAddress(addr);
		
		List<MemberVO> list = new MemberService().findByIdMember(idList);
		
		if(list != null) {
			request.setAttribute("list", list);
			path = "views/allShow.jsp";
		}
		// 데이터바인딩한게 있어서 forward로 보내야햠!
		return new ModelAndView(path);
	}

}
