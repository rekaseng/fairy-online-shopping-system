package model;
import java.util.Date;
import java.util.HashMap;
public class Order {
private int id;
private Date dateCreated;


private String status;
private Shipping shipping;
private HashMap<Product,Integer> product_quantity=new HashMap<Product,Integer>();
private double subtotal;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getDateCreated() {
	return dateCreated;
}
public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Shipping getShipping() {
	return shipping;
}
public void setShipping(Shipping shipping) {
	this.shipping = shipping;
}



public double getSubtotal() {
	return subtotal;
}
public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}
public HashMap<Product, Integer> getProduct_quantity() {
	return product_quantity;
}
public void setProduct_quantity(HashMap<Product, Integer> product_quantity) {
	this.product_quantity = product_quantity;
}


}
