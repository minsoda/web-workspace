package pattern.controller.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pattern.controller.Controller;
import pattern.model.NoteBook;
import pattern.model.NoteBookDAO;

public class FindController implements Controller {

	// findcontroller 비즈니스로직을 짤때 필요해서 미리!!
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String path = "find_fail.jsp";
		// 1.폼값(model 받구있쥬). vo역할인 model 클래스 만들구
		String model = request.getParameter("model");
		NoteBook notebook = NoteBookDAO.getInstance().findNoteBook(model);
		
		if(notebook != null) {
			request.setAttribute("notebook", notebook);
			path = "find_result.jsp";
		}
		return path;
	}
	


}

