package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;

public class ItemViewController implements Controller{
	//이미지를 눌렀을때 쿠키를 저장해야하니까 여기서 작성!
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int itemId = Integer.parseInt(request.getParameter("id"));
		
		// 조회수 추가되어야하는 부분!
		ItemDAO.getInstance().updateRecodeCount(itemId);
		
		Item item = ItemDAO.getInstance().getItem(itemId);
		request.setAttribute("item", item);
		


		// 오늘 본 상품정보를 저장하는 쿠키 로직
		// 1) 쿠키생성 (이미지만 필요한것을 잊지말궁! 구분을 지을수있는 id가 키가 된당!), 키값앞에 구분될수있게 적어준당!
		Cookie cookie = new Cookie("fruit-" + itemId, item.getPictureUrl());
		
		// 쿠키는 시간이 필요하기 떄문에 하루 * 60초 * 60초로 해줌.
		cookie.setMaxAge(24*60*60);
		
		// 2) 응답할때 만든 쿠키 넣기! ( 생성한 쿠키를 웹브라우저로 보냄)
		response.addCookie(cookie);
		
		

		return new ModelAndView("itemView.jsp");
	}

}
