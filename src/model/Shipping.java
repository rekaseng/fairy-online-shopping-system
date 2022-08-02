package model;

import java.util.Date;

public class Shipping {
private int id;
private Address address;

private String courier;
private double costs;
private Date dateShipped;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getCourier() {
	return courier;
}
public void setCourier(String courier) {
	this.courier = courier;
}
public double getCosts() {
	return costs;
}
public void setCosts(double costs) {
	this.costs = costs;
}


public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public Date getDateShipped() {
	return dateShipped;
}
public void setDateShipped(Date dateShipped) {
	this.dateShipped = dateShipped;
}
}
