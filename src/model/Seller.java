package model;

import java.util.ArrayList;

public class Seller {
private int id;
private String password;
private String name;
private String no_tel;

private ArrayList<Product> products;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNo_tel() {
	return no_tel;
}
public void setNo_tel(String no_tel) {
	this.no_tel = no_tel;
}

public ArrayList<Product> getProducts() {
	return products;
}
public void setProducts(ArrayList<Product> products) {
	this.products = products;
}



}
