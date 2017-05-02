package de.sb.messenger.persistence;

import javax.validation.constraints.Size;

public class Message extends BaseEntity {

	private Person author;
	private BaseEntity subject;
	@Size(min = 1, max = 4093)
	private String body;

	public Message(Person author, BaseEntity subject, String body) {
		this.author = author;
		this.subject = subject;
		this.body = body;
	}

	public Person getAuthor() {
		return author;
	}

	public BaseEntity getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public BaseEntity getSubjectReference() {
		return this.subject;
	}
}