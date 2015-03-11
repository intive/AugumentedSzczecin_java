package com.rsawczyn.app;

public class Localization {
	
	private String north;
	private String east;
	
	public Localization(String north, String east) {
		this.north = north;
		this.east = east;
	}
	
	public void setNorth(String north) {
		this.north = north;
	}
	
	public void setEast(String east) {
		this.east = east;
	}
	
	public String getNorth() {
		return this.north;
	}
	
	public String getEast() {
		return this.east;
	}

}
