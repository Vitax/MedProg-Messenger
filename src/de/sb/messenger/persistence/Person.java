package de.sb.messenger.persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person extends BaseEntity {

	private Group group;
	@Pattern(regexp = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])", message = "{invalid.email}")
	@NotNull @Size(min = 1, max = 128)
	private String email;
	@NotNull @Size(min = 32, max = 32)
	private byte[] passwordHash;
	@Valid
	private Name name;
	@Valid
	private Address address;
	@Valid
	private Document avatar;
	private Set<Message> messages;
	private Set<Person> peopleObserving;
	private Set<Person> peopleObserved;

	public Person(Group group, String email) {
		this.group = group;
		this.email = email;
	}

	protected Person() {
		this.name = null;
		this.address = null;
		this.group = null;
		this.email = null;
		this.avatar = null;
	}

	static public enum Group {
		ADMIN, USER
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Name getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public Document getAvatar() {
		return avatar;
	}

	public void setAvatar(Document avatar) {
		this.avatar = avatar;
	}

	public Set<Message> getMessagesList() {
		return messages;
	}

	public void setMessagesList(Set<Message> messagesList) {
		this.messages = messagesList;
	}

	public Set<Person> getPeopleObserving() {
		return peopleObserving;
	}

	public Set<Person> getPeopleObserved() {
		return peopleObserved;
	}

	@Size(min = 32, max = 32)
	static public byte[] passwordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"));
	}
}
