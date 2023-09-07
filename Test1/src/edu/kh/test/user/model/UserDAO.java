package edu.kh.test.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import serverInfo.ServerInfo;

public class UserDAO implements UserDAOTamplate{
	
	public UserDAO() {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL,ServerInfo.USER_NAME,ServerInfo.PASSWORD);
	
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		closeAll(ps, conn);
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		closeAll(rs,ps,conn);
	}

	@Override
	public UserDTO search (int id) throws SQLException {
		Connection conn = getConnection();
		String query = "SELECT * FROM TB_USER WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		ps.setInt(id, 1);
		
		UserDTO dto = null;
		if(rs.next()) {
			dto = new UserDTO(rs.getInt("userNo"),rs.getString("userId"),rs.getString("userName"),rs.getInt("userAge"));
		}
		
		return dto;
	}
	
	
}
