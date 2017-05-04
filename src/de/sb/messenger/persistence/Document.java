package de.sb.messenger.persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.htw.test.model.Gruppe;

@Entity
@Table(name = "Document")
public class Document {

	@Id
	@GeneratedValue
	@Column(name = "DocumentId")
	private long id;
    
	@Column(name = "contentHash")
	@NotNull @Size(min = 32, max = 32)
	private byte[] contentHash;
	
	@Column(name = "contentType")
	@NotNull @Size(min = 1, max = 63) @Pattern(regexp = "^[a-z]+\\/[a-z\\.+\\-]+$")
	private String contentType;
	
	@Column(name = "content")
	@NotNull @Size(min = 1, max = 16777215)
	private byte[] content;
	
	@OneToMany(mappedBy = "Person" , cascade = CascadeType.ALL)
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
