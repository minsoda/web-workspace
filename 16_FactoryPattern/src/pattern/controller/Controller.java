package pattern.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	// findcontroller 비즈니스로직을 짤때 필요해서 미리!!
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
