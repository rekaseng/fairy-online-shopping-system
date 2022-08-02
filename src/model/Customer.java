package model;

import java.util.ArrayList;

public class Customer {
private int id;
private String password;
private String name;
private Address address;
private ArrayList<Order> orders;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public ArrayList<Order> getOrders() {
	return orders;
}
public void setOrders(ArrayList<Order> orders) {
	this.orders = orders;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
