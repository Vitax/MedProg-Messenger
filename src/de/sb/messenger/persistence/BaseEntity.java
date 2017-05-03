package de.sb.messenger.persistence;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

public class BaseEntity implements Comparable<BaseEntity> {
	@Min(value = 0)
	private long identiy;
	@Min(value = 1)
	private int version;
	private long creationTimestamp;
	private Set <Message> messagesCaused;

	public BaseEntity() {
		this.identiy = 0;
		this.version = 1;
		this.creationTimestamp = System.currentTimeMillis();
	}

	public long getIdentiy() {
		return identiy;
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


	public Set <Message> getMessagesCaused() {
		return messagesCaused;
	}

	public void setMessagesCaused(Set<Message> messagesCaused) {
		this.messagesCaused = messagesCaused;
	}

	@Override
	public int compareTo(final BaseEntity obj) {
		return Long.compare(this.identiy, obj.identiy);
	}
}
