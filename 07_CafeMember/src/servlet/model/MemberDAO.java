package servlet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;

public class MemberDAO implements MemberDAOTemplate{
	public static void main(String[] args) {
		
		MemberDAO dao = new MemberDAO();
		try {
//			dao.insertMember(new MemberVO("마마마", 3, "바바바"));
//			잘 나오는지 test.
//			for(MemberVO vo : dao.showAllMember()) {
//				System.out.println(vo);
//			}
			System.out.println(dao.findByNameMember("이름름"));
		} catch (SQLException e) {}
}
	
	public MemberDAO() {
		// 1. 드라이버 로딩
		try {
//			config.serverinfo로 따로 기재(driver_name, url, user, password)
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading Sucess...");	
			
		} catch (ClassNotFoundException e) {}
	}
	

	@Override
	public Connection getConnection() throws SQLException {
		// 2. 데이터베이스와 연결_사용자, 비밀번호
//		config.serverinfo로 따로 기재(driver_name, url, user, password)
	Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	System.out.println("DB Connection..!");
	return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		// 5. close 닫기
		ps.close();
		conn.close();
		
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		closeAll(ps, conn);
		
	}

	@Override
	public void insertMember(MemberVO vo) throws SQLException {
		Connection conn = getConnection();// 위의  conn 함수 불러와융
		
		// 3. Statement 객체 생성(sql문 넣어주세용)
		String query = "INSERT INTO TEST(NAME, AGE, ADDR) VALUES (?, ?, ?)"; // 원래 파일로 빼놨지만 이번은 간단하게 함.
		PreparedStatement ps = conn.prepareStatement(query);
		
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getAge());
			ps.setString(3, vo.getAddr());
		
		// 4. 쿼리문 실행
		System.out.println(ps.executeUpdate() + "명 가입!");
		
		closeAll(ps, conn);
	}

	@Override
	public ArrayList<MemberVO> showAllMember() throws SQLException {
		Connection conn = getConnection();
		
		// 3. Statement 객체 생성(sql문 넣어주세용)
		String query = "SELECT * FROM TEST";
		PreparedStatement ps = conn.prepareStatement(query);
		
		// 4. 쿼리문 실행
		ArrayList<MemberVO> alllist = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			alllist.add(new MemberVO(rs.getString("name"), rs.getInt("age"), rs.getString("addr")));
		}
		closeAll(rs, ps, conn);
		
		return alllist;
	}

	@Override
	public MemberVO findByNameMember(String name) throws SQLException {
		Connection conn = getConnection();
		
		// 3. Statement 객체 생성(sql문 넣어주세용)
		String query = "SELECT * FROM TEST WHERE NAME = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		
		// 4. 쿼리문 실행
		MemberVO v = null;
		if(rs.next()) {
			v = new MemberVO(name, rs.getInt("age"), rs.getString("addr"));
		}
		
		closeAll(rs, ps, conn);
		
		return v;
	}
}
