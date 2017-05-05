package de.sb.messenger.persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
@DiscriminatorValue(value = "Person")
@PrimaryKeyJoinColumn(name="identity")
public class Person extends BaseEntity {

	@Column(name = "group")
	@Enumerated
	private Group group;
	
	@Column(name = "email")
	@Pattern(regexp = "/\\S+\\@\S+\\.\\S+/", message = "{invalid.email}")
	@NotNull @Size(min = 1, max = 128)
	private String email;
	
	@Column(name = "passwordHash")
	@NotNull @Size(min = 32, max = 32)
	private byte[] passwordHash;

	@Embedded 
	@Valid
	//@OneToOne
	private Name name;

	@Embedded 
	@Valid
	//@OneToOne
	private Address address;
	
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="identity")
	private Document avatar;
	
	@OneToMany(mappedBy = "identity")
	private Set<Message> messages;
	
	@ManyToMany(mappedBy = "peopleObserved_REL") //cascade = CascadeType.REMOVE
	private Set<Person> peopleObserving;
	
	@ManyToMany
	@JoinColumn(name="peopleObserved_REL")
	private Set<Person> peopleObserved;

	public Person(Group group, String email) {
		this.group = group;
		this.email = email;
		this.name = null;
		this.address = null;
		this.avatar = null;
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
