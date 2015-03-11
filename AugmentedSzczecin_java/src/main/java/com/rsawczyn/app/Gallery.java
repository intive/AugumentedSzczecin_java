package com.rsawczyn.app;

import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

public class Gallery {
	private List<Image> images;
	
	public Gallery() {
		images = new ArrayList<Image>();
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<Image> getImages() {
		return this.images;
	}

}
