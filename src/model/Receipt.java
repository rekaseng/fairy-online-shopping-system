package model;

public class Receipt {
private int order_Id;
private String custName;
private String productName;
private int quantity;
private double amount;

public int getOrder_Id() {
	return order_Id;
}
public void setOrder_Id(int order_Id) {
	this.order_Id = order_Id;
}	
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}
