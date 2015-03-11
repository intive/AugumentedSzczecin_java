package com.rsawczyn.app;

import java.util.List;

public class Availability {
	
	private List<String> days;
	private List<String> startHour;
	private List<String> stopHour;
	
	public Availability(List<String> days, List<String> startHour, List<String> stopHour) {
		this.days = days;		
		this.startHour = startHour;
		this.stopHour = stopHour;
	}
	
	public void setDays(List<String> days) {
		this.days = days;
	}
	
	public void setStartHour(List<String> startHour) {
		this.startHour = startHour;
	}
	
	public void setStopHour(List<String> stopHour) {
		this.stopHour = stopHour;
	}
	
	public List<String> getDays() {
		return days;
	}
	
	public List<String> getStartHour() {
		return this.startHour;
	}
	
	public List<String> getStopHour() {
		return this.stopHour;
	}

}
