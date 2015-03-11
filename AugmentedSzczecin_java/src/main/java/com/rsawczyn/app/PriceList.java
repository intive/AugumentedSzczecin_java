package com.rsawczyn.app;

import java.math.BigDecimal;
import java.util.List;

public class PriceList {
	
	private List<String> things;
	private List<BigDecimal> prices;
	
	public PriceList(List<String> things, List<BigDecimal> prices) {
		this.things = things;
		this.prices = prices;
	}
	
	public void setThings(List<String> things) {
		this.things = things;
	}
	
	public void setPrices(List<BigDecimal> prices) {
		this.prices = prices;
	}
	
	public List<BigDecimal> getPrices() {
		return this.prices;
	}
	
	public List<String> getThings() {
		return this.things;
	}

}
