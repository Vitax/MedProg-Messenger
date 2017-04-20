package de.sb.messenger.persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Document extends BaseEntity {
	
	@NotNull @Size(min = 32, max = 32)
	private byte[] contentHash;
	@NotNull @Size(min = 1, max = 63) @Pattern(regexp = "^[a-z]+\\/[a-z\\.+\\-]+$")
	private String contentType;
	@NotNull @Size(min = 1, max = 16777215)
	private Blob content;
	
	public Document(byte[] contentHash, String contentType, Blob content) { 
		this.contentHash = contentHash;
		this.contentType = contentType;
		this.content = content;
	}
	
	public byte[] getContentHash() {
		return contentHash;
	}

	public void setContentHash(byte[] contentHash) {
		this.contentHash = contentHash;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public byte[] mediaHash(Blob content) throws NoSuchAlgorithmException, SQLException {
		return MessageDigest.getInstance("SHA-256").digest(content.getBytes(1, (int)content.length()));
	}
}
