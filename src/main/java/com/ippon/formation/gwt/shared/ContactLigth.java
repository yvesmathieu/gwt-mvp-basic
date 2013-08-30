package com.ippon.formation.gwt.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContactLigth implements Serializable {

	private String id;
	
	private String displayName;

	public ContactLigth() {
		this("0", "");
	}
	
	public ContactLigth(String id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


}
