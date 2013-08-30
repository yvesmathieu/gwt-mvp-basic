package com.ippon.formation.gwt.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable {

	String id;
	
	String firstName;
	
	String lastName;
	
	String emailAddress;

	public Contact() {
		
	}
	
	public Contact(String id, String firstName, String lastName, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public ContactLigth getLightContact() {
		return new ContactLigth(id, firstName + " " + lastName);
	}
	
	
}
