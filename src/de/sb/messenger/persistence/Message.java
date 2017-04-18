package de.sb.messenger.persistence;

import javax.validation.constraints.Size;

public class Message extends BaseEntity {
	
	private Person author;	
	private BaseEntity subject;
	@Size(min = 1, max = 4093)
	private String body;
	
	public Person getAuthor() {                     
		return author;                              
    }

    public void setAuthor(Person author) {          
    	this.author = author;                       
    }                                               
    public BaseEntity getSubject() {                
    	return subject;                             
    }                                               
    public void setSubject(BaseEntity subject) {    
    	this.subject = subject;                     
    }                                               
    public String getBody() {                       
    	return body;                                
    }                                               
    public void setBody(String body) {              
    	this.body = body;                           
    }   
}                                            