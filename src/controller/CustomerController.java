package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.PreparedStatement;

import database.MyDatabase;
import model.Customer;
public class CustomerController {
public boolean login(Customer customer) throws ClassNotFoundException, SQLException{
boolean success=false;
	String stat="Select * FROM customer WHERE id = ? AND password = ?";
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	
	preparedStatement.setInt(1, customer.getId());
	preparedStatement.setString(2, customer.getPassword());

	ResultSet resultSet = preparedStatement.executeQuery();
	if(resultSet.next()) 
		success=true;

	con.close();
	return success;
}

public Customer getCustomer_Name(int id) throws ClassNotFoundException, SQLException {
	Customer customer=new Customer();
String stat="Select id,name FROM customer WHERE id=?";
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	preparedStatement.setInt(1, id);
	
	ResultSet resultSet=preparedStatement.executeQuery();
	
	resultSet.next();
	customer.setId(resultSet.getInt(1));
	customer.setName(resultSet.getString(2));
	
	con.close();
	return customer;
}

public int register(Customer customer) throws SQLException, ClassNotFoundException
{
	int success = -1;
	int last=0;
	String sql = "insert into customer (name, password) values (?,?)";
	if(findUser(customer.getName())==false)
	{
	
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setString(1, customer.getName());
		preparedStatement.setString(2, customer.getPassword());
		success = preparedStatement.executeUpdate();
	
		sql="SELECT id FROM customer order by id desc limit 1";
		preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
		ResultSet resultset= preparedStatement.executeQuery();
		
		resultset.next();
		last=resultset.getInt(1);
		
		
		conn.close();
	}
	
	return last;		
	
}

public boolean findUser(String name) throws ClassNotFoundException, SQLException
{
	boolean success = false;
	
	String sql = "select name from customer where name = ?";
	Connection conn = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setString(1, name);
	
	ResultSet resultSet = preparedStatement.executeQuery();
	if(resultSet.next())
		success=true;
	
	conn.close();
	
	return success;
}
public int updatePassword(int customer_id,String newPassword) throws ClassNotFoundException, SQLException {
	String sql="UPDATE customer SET password=? WHERE id=?";
	Connection conn = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	preparedStatement.setString(1, newPassword);
	preparedStatement.setInt(2, customer_id);
	
	int success=preparedStatement.executeUpdate();
	return success;
	
}
}
