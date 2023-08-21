package controller;

public class ModelAndView {
	//path로 전달할거니까!
	private String path;
	private boolean isRedirect;
	
	public ModelAndView() {}
	
	//path만 있는걸로 만들기!
	public ModelAndView(String path) { // forward 방식!
		this.path = path;
		
	}
	// 둘다 있는것도 만들기!
	public ModelAndView(String path, boolean isRedirect) { // sendRedirect! true 같이 넘겨서
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
	
	
}
