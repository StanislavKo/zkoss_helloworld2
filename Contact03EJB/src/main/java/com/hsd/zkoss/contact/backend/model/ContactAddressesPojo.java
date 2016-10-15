package com.hsd.zkoss.contact.backend.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ContactAddressesPojo implements Serializable {

	private ContactPojo contact;
	private List<AddressPojo> addresses = new LinkedList();
	
	public ContactAddressesPojo() {
		super();
	}

	public ContactPojo getContact() {
		return contact;
	}

	public void setContact(ContactPojo contact) {
		this.contact = contact;
	}

	public List<AddressPojo> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressPojo> addresses) {
		this.addresses = addresses;
	}

}
