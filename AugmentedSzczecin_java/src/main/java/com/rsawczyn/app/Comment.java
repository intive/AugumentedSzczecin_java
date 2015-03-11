package com.rsawczyn.app;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	
	private final String ownerName;
	private String content;
	private int rating;
	private final String dateOfcomment;
	private long id;
	
	public Comment(long id, String ownerName, String content) {
		this.id = id;		
		this.ownerName = ownerName;
		this.content = content;
		this.rating = 0;		
		SimpleDateFormat dateOfcomment = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.dateOfcomment = dateOfcomment.format(new Date());
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
/*	public void setContent(String content) {
		this.content = content;
	} */
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public String getDateofComment() {
		return this.dateOfcomment;
	}
}
