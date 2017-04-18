package de.sb.messenger.persistence;

import java.util.List;

public class BaseEntity {
	private double identiy;
	private int version;
	private double creationTimestamp;
	private List<Message> messagesCaused;
	
	public double getIdentiy() {
		return identiy;
	}
	
	public void setIdentiy(double identiy) {
		this.identiy = identiy;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public double getCreationTimestamp() {
		return creationTimestamp;
	}
	
	public void setCreationTimestamp(double creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public List<Message> getMessagesCaused() {
		return messagesCaused;
	}
	
	public void setMessagesCaused(List<Message> messagesCaused) {
		this.messagesCaused = messagesCaused;
	}
}
