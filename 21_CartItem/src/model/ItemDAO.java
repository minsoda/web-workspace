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
			Item item = new Item();
			item.setItemId(rs.getInt("ITEM_ID"));
			item.setItemName(rs.getString("ITEM_NAME"));
			item.setPrice(rs.getInt("PRICE"));
			item.setDescription(rs.getString("DESCRIPTION"));
			item.setPictureUrl(rs.getString("PICTURE_URL"));
			item.setCount(rs.getInt("COUNT"));
			list.add(item);
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
			item.setItemId(rs.getInt("ITEM_ID"));
			item.setItemName(rs.getString("ITEM_NAME"));
			item.setPrice(rs.getInt("PRICE"));
			item.setDescription(rs.getString("DESCRIPTION"));
			item.setPictureUrl(rs.getString("PICTURE_URL"));
			item.setCount(rs.getInt("COUNT"));
			
		}
		closeAll(rs,ps,conn);
		return item;
	}

	@Override
	public boolean updateRecodeCount(int itemId) throws SQLException {
		Connection conn = getConnection();
		
		String query = "UPDATE ITEM SET COUNT =? WHERE ITEM_ID =?";
		PreparedStatement st = conn.prepareStatement(query);
		
		int count = getItem(itemId).getCount();
		
		st.setInt(1, ++count);
		st.setInt(2, itemId);
		
		int result = st.executeUpdate();
		
		if(result == 1) {
			return true;
		}
		return false;
	}

}
