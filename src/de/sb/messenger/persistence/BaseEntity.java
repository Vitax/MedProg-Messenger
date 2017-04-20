package de.sb.messenger.persistence;

import java.util.List;

import javax.validation.constraints.Min;

public class BaseEntity implements Comparable{
	@Min(value = 0)
	private long identity;
	@Min(value = 0)
	private int version;
	@Min(value = 0)
	private long creationTimestamp;
	private List<Message> messagesCaused;
	
	public BaseEntity(long identity, int version, long creationTimestamp) {
		this.identity = identity;
		this.version = version;
		this.creationTimestamp = creationTimestamp;
	}
	
	public BaseEntity() {
		this.identity = 0;
		this.version = 0;
		this.creationTimestamp = 0;
	}
	
	public double getIdentity() {
		return identity;
	}
	
	public void setIdentity(long identiy) {
		this.identity = identiy;
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
	
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public List<Message> getMessagesCaused() {
		return messagesCaused;
	}
	
	public void setMessagesCaused(List<Message> messagesCaused) {
		this.messagesCaused = messagesCaused;
	}

	@Override
	public int compareTo(final BaseEntity obj) {
		return Integer.compare(this.version, obj.version);
	}
}
