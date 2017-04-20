package de.sb.messenger.persistence;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Name {
	@NotNull @Size(min = 1, max = 31)
	private String given;
	@NotNull @Size(min = 1, max = 31)
	private String family;
	
	public Name(String given, String family) {
		this.given = given;
		this.family = family;
	}
	
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
