package com.hsd.zkoss.contact.ui.model.utils;

import org.apache.log4j.Logger;

import com.hsd.zkoss.contact.backend.model.AddressPojo;
import com.hsd.zkoss.contact.backend.model.ContactPojo;
import com.hsd.zkoss.contact.ui.model.ContactViewModel;
import com.hsd.zkoss.contact.ui.model.bean.UIAddress;
import com.hsd.zkoss.contact.ui.model.bean.UIContact;

public class ModelUiUtils {

	private static Logger logger = Logger.getLogger(ContactViewModel.class);

	public static ContactPojo createContactPojo(UIContact uiContact) {
		logger.info("createContactPojo()");
		ContactPojo contactPojo = new ContactPojo();
		contactPojo.setUuid(uiContact.getUuid());
		contactPojo.setFirstName(uiContact.getFirstName());
		contactPojo.setLastName(uiContact.getLastName());
		contactPojo.setMiddleName(uiContact.getMiddleName());
		contactPojo.setPhone(uiContact.getPhone());
		return contactPojo;
	}
	
	public static AddressPojo createAddressPojo(UIAddress uiAddress) {
		logger.info("createAddressPojo()");
		AddressPojo addressPojo = new AddressPojo();
		addressPojo.setUuid(uiAddress.getUuid());
		addressPojo.setPostalCode(uiAddress.getPostalCode());
		addressPojo.setCity(uiAddress.getCity());
		addressPojo.setStreet(uiAddress.getStreet());
		addressPojo.setBuilding(uiAddress.getBuilding());
		addressPojo.setApartment(uiAddress.getApartment());
		return addressPojo;
	}
	
	public static UIContact createContactUi(ContactPojo contactPojo) {
		logger.info("createContactUi()");
		UIContact uiContact = new UIContact();
		uiContact.setUuid(contactPojo.getUuid());
		uiContact.setFirstName(contactPojo.getFirstName());
		uiContact.setLastName(contactPojo.getLastName());
		uiContact.setMiddleName(contactPojo.getMiddleName());
		uiContact.setPhone(contactPojo.getPhone());
		return uiContact;
	}
	
	public static UIAddress createAddressUi(AddressPojo addressPojo) {
		logger.info("createAddressUi()");
		UIAddress uiAddress = new UIAddress();
		uiAddress.setUuid(addressPojo.getUuid());
		uiAddress.setPostalCode(addressPojo.getPostalCode());
		uiAddress.setCity(addressPojo.getCity());
		uiAddress.setStreet(addressPojo.getStreet());
		uiAddress.setBuilding(addressPojo.getBuilding());
		uiAddress.setApartment(addressPojo.getApartment());
		return uiAddress;
	}
	
}
