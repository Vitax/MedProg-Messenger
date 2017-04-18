package de.sb.messenger.persistence;

import javax.validation.constraints.Size;

public class Name {
	@Size(min = 1, max = 31)
	private String given;
	@Size(min = 1, max = 31)
	private String family;
	
	public String getGiven() {
		return given;
	}
	
	public void setGiven(String given) {
		this.given = given;
	}
	
	public String getFamily() {
		return family;
	}
	
	public void setFamily(String family) {
		this.family = family;
	}
}
