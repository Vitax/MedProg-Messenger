package de.sb.messenger.persistence;

import javax.validation.constraints.Size;

public class Address {
	
	@Size(min = 0, max = 63)
	private String street;
	@Size(min = 0, max = 15)
	private String postcode;
	@Size(min = 1, max = 63)
	private String city;
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}
