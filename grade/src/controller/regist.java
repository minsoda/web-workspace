package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class regist
 */
stu

private MemberRepository repo = new MemberRepository();

public class regist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}

CREATE TABLE BOARD (
NO NUMBER PRIMARY KEY,
TITLE VARCHAR2(50) NOT NULL,
CONTENT VARCHAR2(500) NOT NULL,
WRITER VARCHAR2(30) REFERENCES MEMBER(ID),
STATUS VARCHAR2(1) CHECK(STATUS IN ('Y','N')) DEFAULT 'Y',
CREATE_DATE DATE DEFAULT SYSDATE,
UPDATE_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN BOARD.NO IS '글번호';

String query="SELECT NO" + "TITLE"+..+"WHERE B.STATUS="Y"";

java.sql.Date 년.월.일
java.sql.Timestamp 년.월.일.시.분.초.밀리초.지역대 정보 관리

web server 정적인 컨텐츠 제공
web application server 동적.정적인 컨텐츠 제공
was는 동적인 컨텐츠인 db 조회나 업무로직을 처리하고 단순한 정적인 컨텐츠를 web server에 돌리는 편


			
public ArrayList<MemberDTO> showAllMember() throws SQLException{
return repo.showAllMember();
}



public class MemberDAO implements MemberDAOTemplate
private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
Class.forName(ServerInfo.DRIVER_NAME);
public static MemberDAO getInstance() {
return dao;	
}

public interface ServerInfo {
	String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "sample";
	String PASSWORD = "sample";

<%
	String name = request.getParameter("name");
	List<MemberVO> list = (List) request.getAttribute("list");
%>
<a href="/index.jsp">첫 페이지로 이동</a>
<% } else { %>
	<h3>로그인 실패! 로그인부터 하고 오세요</h3>
	<a href="login.html">login.html</a>
<% } %>

<c:choose>
	<c:when test="${!empty dto}">
<a href="/index.jsp">첫 페이지로 이동</a>
	</c:when>
	<c:otherwise>
<%
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=list.get(i).getName()%></td>
				<td><%=list.get(i).getAge()%></td>
				<td><%=list.get(i).getAddr()%></td>
			</tr>
			<%
			}
			}
			%>
<c:forEach items="${list}" var="item">
			<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.address}</td>
			</tr>
		</c:forEach>
public interface MemberDAOTemplate {
	Connection getConnection() throws SQLException;
	void closeAll(PreparedStatement ps, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
public MemberDAO() {	Class.forName(ServerInfo.DRIVER_NAME);}

Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);

Connection conn = getConnection();
String query = "INSERT INTO TEST(NAME, AGE, ADDR) VALUES (?, ?, ?)";
PreparedStatement ps = conn.prepareStatement(query);

ResultSet rs = ps.executeQuery();
do get @WebServlet("/member/all")
String name = request.getParameter("name");
MemberDAO dao = new MemberDAO();
reposity new MemberService().showAllMember();
vo = dao.findByNameMember(name);
request.setAttribute("vo", vo);
request.getRequestDispatcher("view.jsp").forward(request, response);

<typeAliases>
	<typeAlias type="model.vo.DepartmentVO" alias="Department"/>
	<typeAlias type="model.vo.StudentVO" alias="Student"/>
</typeAliases>
<mappers>
	<mapper resource="mappers/student-mapper.xml"/>
</mappers>
<properties/>
<property name=drivername value=>

<insert id="registerMember" parameterType="Member">
		INSERT INTO member(id, password, name, address)
		VALUES(#{id}, #{password}, #{name}, #{address})
	</insert>
<resultMap type="Student" id="studentMap">
	<id property="studentNo" column="student_no"/>
	<result property="studentName" column="student_name"/>

select resultType

Template
public static SqlSession getSqlSession()
	SqlSession session = null;
		
	String resource = "/mybatis-config.xml";
	InputStream stream = Resources.getResourceAsStream(resource);	
	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
	SqlSessionFactory factory = builder.build(stream);
	session = factory.openSession(false);		
return session;
	
Service
SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
int result = MemberDAO.getInstance().s
sqlSession.close();
select xx if(result > 0) sqlSession.commit();

request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");

페이지 스코프 (Page Scope):페이지 스코프는 같은 JSP 페이지 내에서만 유효한 스코프
요청 스코프 (Request Scope):요청 스코프는 하나의 HTTP 요청 내에서 데이터를 공유하기 위한 스코프
세션 스코프 (Session Scope):세션 스코프는 사용자 단위로 데이터를 유지하기 위한 스코프
어플리케이션 스코프 (Application Scope): 어플리케이션 스코프는 웹 애플리케이션 전체에서 공유되는 데이터를 저장하기 위한 스코프

Ajax란?
JavaScript를 이용하여 비동기식으로 클라이언트와 서버가 데이터(XML)를 주고받는(통신) 방식
- url : 요청(request) 데이터를 전송할 URL
- type : Http 요청 방식 지정 (GET / POST)
- data : 서버로 전송할 요청 Parameter 설정
- datatype : 서버의 응답(response) 데이터의 형식(xml, text, json, html 등) 지정, 미 작성시 자동으로 판단하여 지정
