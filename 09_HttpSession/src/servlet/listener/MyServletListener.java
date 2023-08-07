package servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletListener implements ServletContextListener {
	//자바 스크립트의 이벤트 같은 쨔슥!
   
	private ServletContext context; // 가지고 올 아이 변수로 지정하공~
	
    public void contextDestroyed(ServletContextEvent sce)  { 
      System.out.println("contextDestroyed..");
    }

    // dd(web.xml) 읽고 - ServletContext 생성 - ServletContextEvent 발생 - 리스너 감지 - 호출
    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("contextIntialized..");
        
        // sce에서 ServletContext를 받아온다. , 위의 변수로 가져오쟝~
        context = sce.getServletContext();
        context.setAttribute("context", "contextData..01");
        
    }
	
}
