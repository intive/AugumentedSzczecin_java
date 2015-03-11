package com.rsawczyn.app;

public class Address {
	
	private String country;
	private String city;
	private String street;
	private String fax;
	
	public Address(String country, String city, String street, String fax) {
		this.country = country;		
		this.city = city;
		this.street = street;
		this.fax = fax;		
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public String getFax() {
		return this.fax;
	}

}
