package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.ItemDAO;

public class ItemViewController implements Controller{

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int itemId = Integer.parseInt(request.getParameter("ITEM_ID"));
		Item item = ItemDAO.getInstance().getItem(itemId);
		
	
		return new ModelAndView("itemView.jsp");
	}

}
