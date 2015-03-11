package com.rsawczyn.app;

public class Owner {
	
	private String name;
	private String email;
	private String phoneNumber;
	
	public Owner(String name, String email, String phoneNumber) {
		this.name = name;		
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

}
