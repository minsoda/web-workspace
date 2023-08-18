package servlet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;
import servlet.model.vo.MemberDTO;

// 시험 (MemberRepository = MemberDAO)
public class MemberRepository implements MemberDAOTemplate{
	

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
	
	
//	public static void main(String[] args) {
//		MemberDAO dao = new MemberDAO();
//		MemberDTO dto = new MemberDTO();
//		dto.setId("a");
//		dto.setPassword("a");
//		dto.setName("a");
//		dto.setAddress("a");
//		
//		try {
////			dao.registerMember(dto);
//			dto = dao.login("user1", "user1");
//			System.out.println("name : " + dto.getName());
//			System.out.println("address : " + dto.getAddress());
//		} catch (SQLException e) {}
//		
//	}
	
}

