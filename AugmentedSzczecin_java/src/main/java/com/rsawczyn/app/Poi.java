package com.rsawczyn.app;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poi {
	
	private List<Comment> comments;
	private List<Link> links;
	private List<String> tags;
	private List<String> phoneNumbers;	
	
	private long id;
	private String name;
	private String description;
	private String category;
	private Gallery gallery;
	private Localization localization;
	private Availability availability; // dni i  godziny otwarcia
	private PriceList priceList;
	private Owner owner;
	private Address address;
	
	public Poi(long id, String name, List<Comment> comments, List<Link> links, List<String> tags,
			List<String> phoneNumbers, String description, String category, Gallery gallery,
			Localization localization, Availability availability, PriceList priceList, Owner owner, Address address) {
		this.id = id;
		this.name = name;
	}
	
	public Poi(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Poi(long id, String name, String category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}
	
	@JsonProperty
	 public void setName(String name) {
		 this.name = name;
	 }
	@JsonProperty
	 public void setCategory(String category) {
		 this.category = category;
	 }
	
	public void setDescription(String description) {
		 this.description = description;
	 }
	 
	 public void setGallery(Gallery gallery) {
		 this.gallery = gallery;
	 }
	 
	 public void setLocalization(Localization localization) {
		 this.localization = localization;
	 }
	 
	 public void setAvailability(Availability availability) {
		 this.availability = availability;
	 }
	 
	 public void setPriceList(PriceList priceList) {
		 this.priceList = priceList;
	 }
	 public void setOwner(Owner owner) {
		 this.owner = owner;
	 }
	 public void setAddress(Address address) {
		 this.address = address;
	 }
	 
	 public void setComment(List<Comment> comments) {
		 this.comments = comments;
	 }
	 
	 public void setLink(List<Link> links) {
		 this.links = links;
	 }
	 
	 public void setTags(List<String> tags) {
		 this.tags = tags;
	 }
	 
	 public void setphoneNumbers(List<String> phoneNumbers) {
		 this.phoneNumbers = phoneNumbers;
	 }
	 @JsonProperty
	 public long getId() {
		 return this.id;
	 }
	 @JsonProperty
	 public String getName() {
		 return this.name;
	 }
	 @JsonProperty
	 public String getCategory() {
		 return this.category;
	 }
	 
	 public String getDescription() {
		 return this.description;
	 }
	 public Gallery getGallery() {
		 return this.gallery;
	 }
	 
	 public Localization getLocalization() {
		 return this.localization;
	 }
	 
	 public Availability getAvailability() {
		 return this.availability;
	 }
	 
	 public PriceList getPriceList() {
		 return this.priceList;
	 }
	 public Owner getOwner(Owner owner) {
		 return this.owner;
	 }
	 public Address getAddress() {
		 return this.address;
	 }
	 
	 public List<Comment> getComment() {
		 return this.comments;
	 }
	 
	 public List<Link> getLink() {
		 return this.links;
	 }
	 
	 public List<String> getTags() {
		 return this.tags;
	 }
	 
	 public List<String> getphoneNumbers(List<String> phoneNumbers) {
		 return this.phoneNumbers;
	 }
	 

}
