package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberDTO;

public class MemberDAO implements MemberDAOTemplate{
		
	// 싱글톤 패턴 - 클래스의 객체가 항상 하나만 존재하도록. static 있쥬
	/*
	 * DAO를 반복적으로 생성하고 해제하는 것은 비효율적.
	 * 객체지향적으로 설계! 싱글톤 패톤은 객체지향적 설계 원칙을 준수 -> 중앙에서 처리!
	 * 주의할점 : 싱글톤은 전역 상태를 가질수 있으므로 오남용하면 애플리케이션의 복잡성이 증가!
	 * */
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() { // private라서 밖에서 호출 못해이ㅣㅇ
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {}
		}
	public static MemberDAO getInstance() {
		return dao;	
		}
	
	// 기본 방법
//	public MemberDAO() {
//		try {
//			Class.forName(ServerInfo.DRIVER_NAME);
//		} catch (ClassNotFoundException e) {}
//	}
	
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
	public void registerMember(MemberDTO dto) throws SQLException {
		Connection conn = getConnection();
		String query = "INSERT INTO MEMBER(ID, PASSWORD, NAME, ADDRESS) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getPassword());
		ps.setString(3, dto.getName());
		ps.setString(4, dto.getAddress());
		
		ps.executeUpdate();
		
		closeAll(ps, conn);
	}

	
	public MemberDTO login(String id, String password) throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER WHERE id=? and password=?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		MemberDTO dto = null;
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
		}
		
		closeAll(rs, ps, conn);
		
		return dto;
	}

	@Override
	public MemberDTO findByIdMember(String id) throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		MemberDTO dto = null;
		if(rs.next()) {
			dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
		}
		closeAll(rs,ps,conn);
		return dto;
	}

	@Override
	public ArrayList<MemberDTO> showAllMember() throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setPassword(rs.getString("password"));
			dto.setName(rs.getString("name"));
			dto.setAddress(rs.getString("address"));
			list.add(dto);
			

		}
		closeAll(rs,ps,conn);
		return list;
	}
	
	@Override
	public void updateMember(MemberDTO dto) throws SQLException {
		Connection conn = getConnection();
		String query = "UPDATE MEMBER SET PASSWORD=?, NAME=?, ADDRESS=? WHERE ID=? ";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, dto.getPassword());
		ps.setString(2, dto.getName());
		ps.setString(3, dto.getAddress());
		ps.setString(4, dto.getId());
		
		ps.executeUpdate();
		
		closeAll(ps, conn);
	}
	
	
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		dto.setId("a");
		dto.setPassword("a");
		dto.setName("a");
		dto.setAddress("a");
		
		try {
//			dao.registerMember(dto);
			dto = dao.login("user1", "user1");
			System.out.println("name : " + dto.getName());
			System.out.println("address : " + dto.getAddress());
		} catch (SQLException e) {}
		
	}
	
}
