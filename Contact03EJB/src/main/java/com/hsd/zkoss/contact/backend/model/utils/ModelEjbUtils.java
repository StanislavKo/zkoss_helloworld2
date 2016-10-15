package com.hsd.zkoss.contact.backend.model.utils;

import com.hsd.zkoss.contact.backend.entity.PUAddress;
import com.hsd.zkoss.contact.backend.entity.PUContact;
import com.hsd.zkoss.contact.backend.model.AddressPojo;
import com.hsd.zkoss.contact.backend.model.ContactPojo;

public class ModelEjbUtils {

	public static ContactPojo createContactPojo(PUContact puContact) {
		ContactPojo contactPojo = new ContactPojo();
		contactPojo.setUuid(puContact.getUuid());
		contactPojo.setFirstName(puContact.getFirstName());
		contactPojo.setLastName(puContact.getLastName());
		contactPojo.setMiddleName(puContact.getMiddleName());
		contactPojo.setPhone(puContact.getPhone());
		return contactPojo;
	}
	
	public static AddressPojo createAddressPojo(PUAddress puAddress) {
		AddressPojo addressPojo = new AddressPojo();
		addressPojo.setUuid(puAddress.getUuid());
		addressPojo.setPostalCode(puAddress.getPostalCode());
		addressPojo.setCity(puAddress.getCity());
		addressPojo.setStreet(puAddress.getStreet());
		addressPojo.setBuilding(puAddress.getBuilding());
		addressPojo.setApartment(puAddress.getApartment());
		return addressPojo;
	}
	

}
