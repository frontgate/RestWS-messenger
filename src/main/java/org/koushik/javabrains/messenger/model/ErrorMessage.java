package org.koushik.javabrains.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String message;
	private String document;
	private int errorcode;
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String message, String document, int errorcode) {
		super();
		this.message = message;
		this.document = document;
		this.errorcode = errorcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	
	
	
	
}
