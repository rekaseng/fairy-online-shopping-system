package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.net.httpserver.Authenticator.Success;

import database.MyDatabase;
import model.Address;
import model.Customer;
import model.Order;
import model.Product;
import model.Receipt;

public class OrderController {
	
	public int setOrderDetails(Order order, int customer_id) throws ClassNotFoundException, SQLException {
		int success =0;
		int successOrder=0;
		int last=0;
		for(Product product:order.getProduct_quantity().keySet())
		{
	
		String sql="INSERT INTO orders (dateCreated,status,subtotal,customer_id) VALUES (?,?,?,?)";
		Connection conn=(Connection) MyDatabase.doConnection();
		PreparedStatement preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
		
		java.sql.Date sqlDate= new java.sql.Date(order.getDateCreated().getTime());
	
		preparedStatement.setDate(1, sqlDate);
		preparedStatement.setString(2, order.getStatus());
		preparedStatement.setDouble(3, order.getSubtotal());
		preparedStatement.setInt(4, customer_id);
		
		successOrder=preparedStatement.executeUpdate();
		
		sql="SELECT id FROM orders order by id desc limit 1";
		preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
		ResultSet resultset= preparedStatement.executeQuery();
		
		resultset.next();
		last=resultset.getInt(1);
		
		
		sql="INSERT INTO order_product (order_id,product_id,quantity) VALUES (?,?,?)";
		preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
			
		preparedStatement.setInt(1,last);//to be update
		preparedStatement.setInt(2, product.getCode());
		preparedStatement.setInt(3,order.getProduct_quantity().get(product));

		success =preparedStatement.executeUpdate();
		conn.close();
		}
		
		if(success!=0 &&successOrder !=0)
		{
			return last;//return the new id
			
		}
		else {
			return 0;
		}
	
	
	}
	
	public Receipt getReceiptDetails(int order_Id) throws ClassNotFoundException, SQLException {
		Receipt receipt=new Receipt();
		
		receipt.setOrder_Id(order_Id);
		String sql="SELECT name FROM customer INNER JOIN orders On orders.customer_Id=customer.Id WHERE orders.id=?";
	Connection conn=(Connection) MyDatabase.doConnection();
	PreparedStatement preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
	preparedStatement.setInt(1, order_Id);
	
	ResultSet resultSet=preparedStatement.executeQuery();
	resultSet.next();
	receipt.setCustName(resultSet.getString(1));
	
	sql="SELECT name from product INNER JOIN order_product ON order_product.product_id=product.code WHERE order_product.order_id=?";
	preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
	preparedStatement.setInt(1, order_Id);
	
	resultSet=preparedStatement.executeQuery();
	resultSet.next();
	receipt.setProductName(resultSet.getString(1));
	
	sql="SELECT quantity FROM order_product WHERE order_id=?";
	preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
	preparedStatement.setInt(1, order_Id);
	
	resultSet=preparedStatement.executeQuery();
	resultSet.next();
	receipt.setQuantity(resultSet.getInt(1));
	
	sql="SELECT subtotal FROM orders WHERE id=?";
	preparedStatement=(PreparedStatement)conn.prepareStatement(sql);
	preparedStatement.setInt(1, order_Id);
	
	resultSet=preparedStatement.executeQuery();
	resultSet.next();
	receipt.setAmount(resultSet.getDouble(1));
	
	conn.close();
		
		return receipt;
	}
	
	public Order getOrderDetails(Customer customer, Product product, Address address,int customer_id, int product_code) throws ClassNotFoundException, SQLException
	{
		Order order = new Order();
		
		String sql="SELECT id, name FROM customer WHERE customer_id = ? UNION SELECT code, name, price FROM product WHERE product_code = ? UNION SELECT * FROM address WHERE customer_id =?";
		Connection conn = (Connection) MyDatabase.doConnection();
		PreparedStatement preparedStatement=(PreparedStatement) conn.prepareStatement(sql);
		preparedStatement.setInt(1, customer_id);
		preparedStatement.setInt(2, product_code);
		preparedStatement.setInt(3, customer_id);
		
		ResultSet resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next())
		{
			customer.setId(resultSet.getInt(1));
			product.setCode(resultSet.getInt(2));
			customer.setId(resultSet.getInt(3));
		}
		conn.close();
		return order;
			
	}
	public TreeMap<Integer,String> getOrderId_ProductName(int customer_id)throws ClassNotFoundException, SQLException{
		TreeMap<Integer, String>orderId_ProductName=new TreeMap<Integer, String>();
		String stat="SELECT orders.id,product.name FROM ((orders INNER JOIN order_product ON orders.id=order_product.order_id) INNER JOIN product ON (product.code=order_product.product_id)) WHERE orders.customer_id=? AND UPPER(orders.status)!='CANCEL'";
		Connection conn=(Connection) MyDatabase.doConnection();
		PreparedStatement preparedStatement=(PreparedStatement)conn.prepareStatement(stat);
		preparedStatement.setInt(1, customer_id);
		ResultSet resultSet=preparedStatement.executeQuery();
		
		Integer id;
		String name;
		while(resultSet.next()) {
			id=resultSet.getInt(1);
			name=resultSet.getString(2);
			orderId_ProductName.put(id,name);
		
		}
		conn.close();
		return orderId_ProductName;
		
	}
	public int updateCancelOrderStatus(int order_id)throws ClassNotFoundException, SQLException{
		String stat="UPDATE orders SET status='Cancel' WHERE id=?";
		Connection conn=(Connection) MyDatabase.doConnection();
		PreparedStatement preparedStatement=(PreparedStatement)conn.prepareStatement(stat);
		preparedStatement.setInt(1, order_id);
		int success=preparedStatement.executeUpdate();
		conn.close();
		
		return success;
	}
	
	


	
}
