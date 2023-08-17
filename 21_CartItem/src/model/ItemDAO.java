package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ServerInfo;

public class ItemDAO implements ItemDAOTemplate{

	private static ItemDAO dao = new ItemDAO();
	private ItemDAO() {
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {}
	}
	public static ItemDAO getInstance() {
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
		closeAll(ps, conn);
	}

	@Override
	public ArrayList<Item> getAllItem() throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM ITEM";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Item> list = new ArrayList<>();
		
		while(rs.next()) {
			// 이렇게도 가능!
			list.add(new Item(rs.getInt(1),rs.getString(2) ,rs.getInt(3), rs.getString(4),rs.getString(5),rs.getInt(6)));
		}
		
		closeAll(rs,ps,conn);
		return list;
	}

	@Override
	public Item getItem(int itemId) throws SQLException {
		Connection conn = getConnection();
		
		String query = "SELECT * FROM ITEM WHERE ITEM_ID= ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setInt(1, itemId);
		ResultSet rs = ps.executeQuery();
		
		Item item = null;
		if(rs.next()) {
			item = new Item(rs.getInt(1),rs.getString(2) ,rs.getInt(3), rs.getString(4),rs.getString(5),rs.getInt(6));
			
		}
		closeAll(rs,ps,conn);
		return item;
	}

	@Override
	public boolean updateRecodeCount(int itemId) throws SQLException {
		Connection conn = getConnection();
		
		String query = "UPDATE ITEM SET COUNT = COUNT+1 WHERE ITEM_ID =?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setInt(1, itemId);

		
		int row = ps.executeUpdate();
		
		boolean result = false;
		
		if(row == 1) result = true;
		// row > 0
		return result;
	}

}
