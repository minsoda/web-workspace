package controller.component;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.ModelAndView;
import model.service.StudentService;
import model.vo.StudentVO;

public class FindController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String word = request.getParameter("word");
	
		List<StudentVO> list = new StudentService().showStudent(word);

		//json
		JSONObject json = new JSONObject();
		//jackson
		ObjectMapper mapper = new ObjectMapper();
		
		String result = mapper.writeValueAsString(list);
		
		//json에 result 담기!
		json.put("result", result);
		
		//담은걸 보내기
		PrintWriter out = response.getWriter();
		out.print(json);
		
		return null;
	}

}
