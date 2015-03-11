package com.rsawczyn.app;

public class Link {
	
	private String link;
	private String name;
	private int id;
	
	public Link(int id, String name, String link) {
		this.id = id;		
		this.link = link;
		this.name = name;		
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public String getName() {
		return this.name;
	}

}
