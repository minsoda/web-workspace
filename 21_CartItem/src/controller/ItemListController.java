package controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;

public class ItemListController implements Controller {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// itemlist.jsp 하기 위해서 작성!
		ArrayList<Item> list = ItemDAO.getInstance().getAllItem();
		request.setAttribute("list", list);
		
		
		ArrayList<String> fruits = new ArrayList<>();
		// 저장한 쿠키를 불러와야한다
		// 쿠키 정보 받아오는 로직
		Cookie[] cs = request.getCookies();
		
		if(cs != null) {
			for(Cookie c : cs) {
				//fruit- 으로 시작하는 것만 필요하니까 조건을 걸기!
				if(c.getName().startsWith("fruit-")) {
					// value값(이미지값) 추가해주면댕!
					fruits.add(c.getValue());
				}
			}
		}
		
		// 바인딩해줘야 jsp에서 사용할수있징~
		request.setAttribute("fruits", fruits);
		
		return new ModelAndView("itemList.jsp");
	}

}
