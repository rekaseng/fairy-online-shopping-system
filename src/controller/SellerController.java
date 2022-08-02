package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import database.MyDatabase;
import model.Seller;

public class SellerController
{
	public boolean doLogin(Seller seller) throws ClassNotFoundException, SQLException 
	{
		boolean success=false;
		String sql = "select * from seller where id = ? and password =?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setInt(1, seller.getId());
		preparedStatement.setString(2, seller.getPassword());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) 
			success=true;
		
		conn.close();
		return success;
		
	}
	
	public int insertSeller(Seller seller) throws ClassNotFoundException, SQLException
	{
			int success = -1;
			String sql="insert into seller (password,name,no_tel)values(?,?,?)";
			if(findUser(seller.getName())==false)
			{
			
				Connection conn = MyDatabase.doConnection();
				PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
				preparedStatement.setString(1, seller.getPassword());
				preparedStatement.setString(2, seller.getName());
				preparedStatement.setString(3, seller.getNo_tel());
				success = preparedStatement.executeUpdate();
				
				conn.close();
			}
			
			return success;	
	}
	
	
	
	public boolean findUser(String name) throws ClassNotFoundException, SQLException
	{
		boolean success = false;
		
		String sql = "select name from seller where name = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, name);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			success=true;
		
		conn.close();
		
		return success;
	}
}