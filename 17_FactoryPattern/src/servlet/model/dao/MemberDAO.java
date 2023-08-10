package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberVO;

public class MemberDAO implements MemberDAOTemplate{
		

	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() { // private라서 밖에서 호출 못해이ㅣㅇ
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {}
		}
	public static MemberDAO getInstance() {
		return dao;	
		}
	
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		closeAll(ps,conn); // 메서드명은 같은데, 매개변수가 다른경우 : 오버로딩. 상속받은것을 재정의 : 오버라이딩.
	}

	@Override
	public void registerMember(MemberVO vo) throws SQLException {
		Connection conn = getConnection();
		String query = "INSERT INTO MEMBER(ID, PASSWORD, NAME, ADDRESS) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPassword());
		ps.setString(3, vo.getName());
		ps.setString(4, vo.getAddress());
		
		ps.executeUpdate();
		
		closeAll(ps, conn);
	}

	
	public MemberVO login(String id, String password) throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER WHERE id=? and password=?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		MemberVO vo = null;
		if(rs.next()) {
			vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
		}
		
		closeAll(rs, ps, conn);
		
		return vo;
	}

	@Override
	public MemberVO findByIdMember(String id) throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		MemberVO vo = null;
		if(rs.next()) {
			vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
		}
		closeAll(rs,ps,conn);
		return vo;
	}

	@Override
	public ArrayList<MemberVO> showAllMember() throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<MemberVO> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setName(rs.getString("name"));
			vo.setAddress(rs.getString("address"));
			list.add(vo);
			

		}
		closeAll(rs,ps,conn);
		return list;
	}
	
	@Override
	public void updateMember(MemberVO vo) throws SQLException {
		Connection conn = getConnection();
		String query = "UPDATE MEMBER SET PASSWORD=?, NAME=?, ADDRESS=? WHERE ID=? ";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, vo.getPassword());
		ps.setString(2, vo.getName());
		ps.setString(3, vo.getAddress());
		ps.setString(4, vo.getId());
		
		ps.executeUpdate();
		
		closeAll(ps, conn);
	}
	
	
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setId("a");
		vo.setPassword("a");
		vo.setName("a");
		vo.setAddress("a");
		
		try {
//			dao.registerMember(dto);
			vo = dao.login("user1", "user1");
			System.out.println("name : " + vo.getName());
			System.out.println("address : " + vo.getAddress());
		} catch (SQLException e) {}
		
	}
	
}
