package pattern.action;

// 비지니스에 만들었던 메소드들이 들어갈 예정!(인터페이수~~~)

/*
 * Framework 기술
 * Struts1 --> Struts2 --> Spring Framework
 * */
public interface Action {
	void execute(); // public abstract 생략되어있쥬~ implements 하면 무조건 호출되게~
}
