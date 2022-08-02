package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.TreeSelectionListener;

import com.mysql.jdbc.PreparedStatement;

import database.MyDatabase;
import model.Product;
public class ProductController {
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
public ArrayList<Product> getAllProduct(String selectedCategory) throws ClassNotFoundException, SQLException{
	

	
	ArrayList<Product> products=new ArrayList<Product>();
	String stat="SELECT * FROM product WHERE UPPER(category)=?";
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	preparedStatement.setString(1, selectedCategory.toUpperCase());
	ResultSet resultSet=preparedStatement.executeQuery();
	
	while(resultSet.next())
	{
		Product product=new Product();
		product.setCode(resultSet.getInt(1));
		product.setName(resultSet.getString(2));
		product.setPrice(resultSet.getDouble(3));
		
		
		product.setCategory(resultSet.getString(4));
	
		products.add(product);
	}
	con.close();
	return products;
}
public ArrayList<String> getProductNames(String selectedCategory){
	ArrayList<String>names=new ArrayList<String>();
	ArrayList<Product> products=new ArrayList<Product>();
	try {
		products = getAllProduct(selectedCategory);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(Product product:products) {
		names.add(product.getName());
	}
	
	return names;
}
public ArrayList<String> getAllCategory() throws ClassNotFoundException, SQLException{
	ArrayList<String>categories=new ArrayList<String>();
	String stat="SELECT DISTINCT(category) FROM product";
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	
	
	ResultSet resultSet=preparedStatement.executeQuery();
	
	while(resultSet.next()) {
		categories.add(resultSet.getString(1));
	
	}
	con.close();
	return categories;
}
public Product getProductDetails(Integer id) throws ClassNotFoundException, SQLException {
	Product product=new Product();
	
	String stat="SELECT * FROM product WHERE code=?";
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	preparedStatement.setInt(1, id);
	ResultSet resultSet=preparedStatement.executeQuery();
	
	if (resultSet.next()) {
		
		product.setCode(resultSet.getInt(1));
		product.setName(resultSet.getString(2));
		product.setPrice(resultSet.getDouble(3));
		product.setCategory(resultSet.getString(4));

	}
	con.close();
	return product;
}
public TreeMap<Integer,String>getNames_id(String category)throws ClassNotFoundException, SQLException{
	TreeMap<Integer, String>names_id=new TreeMap<Integer,String>();
	String stat="SELECT code,name FROM product WHERE UPPER(category)=?";
			Connection con;
		
				con = MyDatabase.doConnection();
				
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
			preparedStatement.setString(1, category.toUpperCase());
		ResultSet resultSet=preparedStatement.executeQuery();
		Integer id;
		String name;
		while(resultSet.next()) {
			id=resultSet.getInt(1);
			name=resultSet.getString(2);
			names_id.put(id,name);
		
		}
		con.close();
	return names_id;
}
public int getSeller_Id(int product_code)throws ClassNotFoundException, SQLException {
	String stat="SELECT seller_id FROM product WHERE code=?";
	Connection con;
	con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
	preparedStatement.setInt(1, product_code);
	ResultSet resultSet=preparedStatement.executeQuery();
	resultSet.next();
	int seller_id=resultSet.getInt(1);
	return seller_id;
}
public int addProduct(Product product,int seller_id)throws ClassNotFoundException, SQLException {
	
	int success=-1;
	String sql="insert into product (name,price,category,seller_id) values (?,?,?,?)";
	
	Connection con = MyDatabase.doConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1, product.getName());;
	preparedStatement.setDouble(2, product.getPrice());
	preparedStatement.setString(3, product.getCategory());
preparedStatement.setInt(4, seller_id);

success=preparedStatement.executeUpdate();
con.close();
	return success;
}
public Product searchProduct(String search) throws ClassNotFoundException, SQLException {
Product product=new Product();
String sql="SELECT code,name,price,category FROM product WHERE INSTR(name, ?) ";
Connection conn=(Connection)MyDatabase.doConnection();
PreparedStatement preparedStatement=(PreparedStatement)conn.prepareCall(sql);
preparedStatement.setString(1, search);
ResultSet resultSet=preparedStatement.executeQuery();
resultSet.next();
product.setCode(resultSet.getInt(1));
product.setName(resultSet.getString(2));
product.setPrice(resultSet.getDouble(3));
product.setCategory(resultSet.getString(4));
conn.close();
	return product;
}
public TreeMap<Integer,String> getSearchProduct(String search)throws ClassNotFoundException, SQLException{
	TreeMap<Integer, String>names_id=new TreeMap<Integer,String>();
	String stat="SELECT code,name FROM product WHERE INSTR(name, ?)";
			Connection con;
		
				con = MyDatabase.doConnection();
				
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(stat);
			preparedStatement.setString(1, search);
		ResultSet resultSet=preparedStatement.executeQuery();
		Integer id;
		String name;
		while(resultSet.next()) {
			id=resultSet.getInt(1);
			name=resultSet.getString(2);
			names_id.put(id,name);
		
		}
		con.close();
	return names_id;
}
/*public String getSellerName(String search) {
	String sql="SELECT seller.name FROM product INNER JOIN seller ON seller_id=seller.id  WHERE INSTR(product.name, ?) ";
}*/
}