package de.sb.messenger.persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person extends BaseEntity {

	private Group group; 
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	message="{invalid.email}")
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
	private List<Message> messagesList;
	private List<Person> peopleObserving;
	private List<Person> peopleObserved;
	
	public Person(Name name, Address address, Group group, String email) {
		this.name = name;
		this.address = address;
		this.group = group;
		this.email = email;
	}

	public enum Group {
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

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Document getAvatar() {
		return avatar;
	}

	public void setAvatar(Document avatar) {
		this.avatar = avatar;
	}

	public List<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}

	public List<Person> getPeopleObserving() {
		return peopleObserving;
	}

	public void setPeopleObserving(List<Person> peopleObserving) {
		this.peopleObserving = peopleObserving;
	}

	public List<Person> getPeopleObserved() {
		return peopleObserved;
	}

	public void setPeopleObserved(List<Person> peopleObserved) {
		this.peopleObserved = peopleObserved;
	}

	@Size(min = 32, max = 32)
	public byte[] passwordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8"));
	}
}
