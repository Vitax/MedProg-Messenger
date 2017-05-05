package de.sb.messenger.persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Document")
@DiscriminatorValue(value = "Document")
@PrimaryKeyJoinColumn(name="identity")
public class Document extends BaseEntity {
	
	@Column(name = "contentHash")
	@NotNull @Size(min = 32, max = 32)
	private byte[] contentHash;
	
	@Column(name = "contentType")
	@NotNull @Size(min = 1, max = 63) @Pattern(regexp = "^[a-z]+\\/[a-z\\.+\\-]+$")
	private String contentType;
	
	@Column(name = "content")
	@NotNull @Size(min = 1, max = 16777215)
	private byte[] content;
	
	@OneToMany(mappedBy = "avatar")
	private Person avatar;

	public Document(String contentType, byte[] content) throws NoSuchAlgorithmException, SQLException {
		this.contentHash = mediaHash(content);
		this.contentType = contentType;
		this.content = content;
	}

	protected Document() {
		this.contentHash = null;
		this.contentType = null;
		this.content = null;
	}
	
	public byte[] getContentHash() {
		return contentHash;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	static public byte[] mediaHash(byte[] content) throws NoSuchAlgorithmException, SQLException {
		return MessageDigest.getInstance("SHA-256").digest(content);
	}
}
