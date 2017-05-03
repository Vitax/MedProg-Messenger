package de.sb.messenger.persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Document {

	@NotNull @Size(min = 32, max = 32)
	private byte[] contentHash;
	@NotNull @Size(min = 1, max = 63) @Pattern(regexp = "^[a-z]+\\/[a-z\\.+\\-]+$")
	private String contentType;
	@NotNull @Size(min = 1, max = 16777215)
	private byte[] content;

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
