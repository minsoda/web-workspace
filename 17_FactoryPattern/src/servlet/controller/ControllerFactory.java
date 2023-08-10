package servlet.controller;
// HandlerMapping 이당~~
// 공장기능, 생성기능!
public class ControllerFactory {

	private static ControllerFactory factory = new ControllerFactory();
	private ControllerFactory() {}
	public static ControllerFactory getInstance() {
	return factory;
	}
	
	public Controller createController(String command) {
		Controller controller = null;
		
		if(command.equals("find")) {
			controller = new FindController();
		}
		
		return controller;
	}
	
}
