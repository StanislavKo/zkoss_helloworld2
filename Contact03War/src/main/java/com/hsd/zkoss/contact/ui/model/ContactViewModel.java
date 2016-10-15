package com.hsd.zkoss.contact.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.zkoss.bind.Binder;
import org.zkoss.bind.DefaultBinder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import com.hsd.zkoss.contact.backend.ejb.ContactEjb;
import com.hsd.zkoss.contact.backend.model.AddressPojo;
import com.hsd.zkoss.contact.backend.model.ContactAddressesPojo;
import com.hsd.zkoss.contact.ui.model.bean.UIAddress;
import com.hsd.zkoss.contact.ui.model.bean.UIContact;
import com.hsd.zkoss.contact.ui.model.utils.ModelUiUtils;

public class ContactViewModel {

	private static Logger logger = Logger.getLogger(ContactViewModel.class);

	private String findContactText;
	private UIContact contact = new UIContact(UUID.randomUUID().toString());
	private List<UIAddress> addresses = new ArrayList();
	private ListModelList<UIAddress> addressesModel;

	@Wire("#firstName")
	private Textbox firstName;

	@Wire("#lastName")
	private Textbox lastName;

	@Wire("#middleName")
	private Textbox middleName;

	@Wire("#phone")
	private Textbox phone;

	@Wire("#addressesGrid")
	private Grid addressesGrid;

	// @Wire("#todoList")
	// private Listbox todoList;
	// private ListModelList<TodoItem> todoListModel;

	public String getFindContactText() {
		return findContactText;
	}

	public void setFindContactText(String findContactText) {
		this.findContactText = findContactText;
	}

	public UIContact getContact() {
		return contact;
	}

	public void setContact(UIContact contact) {
		this.contact = contact;
	}

	public List<UIAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UIAddress> addresses) {
		this.addresses = addresses;
	}

	public ListModelList<UIAddress> getAddressesModel() {
		return addressesModel;
	}

	public void setAddressesModel(ListModelList<UIAddress> addressesModel) {
		this.addressesModel = addressesModel;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		logger.info("afterCompose()");

		Session sess = Sessions.getCurrent();
		if (sess.getAttribute("contact") != null) {
			contact = (UIContact) sess.getAttribute("contact");
		}
		if (sess.getAttribute("addresses") != null) {
			addresses = (List<UIAddress>) sess.getAttribute("addresses");
		}
		logger.info("afterCompose() [contact:" + contact + "] [addresses.size:" + addresses.size() + "]");

		Selectors.wireComponents(view, this, false);
	}

	@Command("onCreate")
	public void onCreate() {
		logger.info("onCreate()");

		addressesModel = new ListModelList<UIAddress>(addresses);
		addressesGrid.setModel(addressesModel);
	}

	@Command("newContact")
	@NotifyChange({ "contact", "addresses" })
	public void newContact() {
		logger.info("addContact()");
		contact = new UIContact(UUID.randomUUID().toString());
		addresses.clear();
		addressesModel = new ListModelList<UIAddress>(addresses);
		addressesGrid.setModel(addressesModel);
	}

	@Command("findContact")
	@NotifyChange({ "contact", "addresses" })
	public void findContact() {
		logger.info("findContact() [findContactText:" + findContactText + "]");
		try {
			ContactEjb contactEjb;
			InitialContext context = new InitialContext();
			contactEjb = (ContactEjb) context.lookup("java:module/ContactEjb");

			ContactAddressesPojo contactAddressesPojo = contactEjb.findContact(findContactText);
			
			if (contactAddressesPojo != null) {
				contact = ModelUiUtils.createContactUi(contactAddressesPojo.getContact());
				addresses.clear();
				for (AddressPojo addressPojo : contactAddressesPojo.getAddresses()) {
					addresses.add(ModelUiUtils.createAddressUi(addressPojo));
				} 
				addressesModel = new ListModelList<UIAddress>(addresses);
				addressesGrid.setModel(addressesModel);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Command("editAddress")
	public void editAddress(@BindingParam("target") Component target) {
		logger.info("editAddress()          [target:" + target + "] [id:" + target.getId() + "]");
		String uuid = target.getId().substring("gridrowedit_".length());

		UIAddress address = null;
		for (UIAddress addressCur : addresses) {
			if (addressCur.getUuid().equals(uuid)) {
				address = addressCur;
			}
		}

		if (address != null) {
			Session sess = Sessions.getCurrent();
			sess.setAttribute("contact", contact);
			sess.setAttribute("addresses", addresses);
			sess.setAttribute("address", address);
			Executions.sendRedirect("/address.zul");
		}
	}

	@Command("deleteAddress")
	public void deleteAddress(@BindingParam("target") Component target) {
		logger.info("deleteAddress()          [target:" + target + "] [id:" + target.getId() + "]");
		String uuid = target.getId().substring("gridrowdelete_".length());

		UIAddress address = null;
		for (UIAddress addressCur : addresses) {
			if (addressCur.getUuid().equals(uuid)) {
				address = addressCur;
			}
		}

		if (address != null) {
			addresses.remove(address);
			addressesModel = new ListModelList<UIAddress>(addresses);
			addressesGrid.setModel(addressesModel);
		}
	}

	@Command("addAddress")
	public void addAddress() {
		logger.info("addAddress()");
		
		contact.setFirstName(firstName.getValue());
		contact.setLastName(lastName.getValue());
		contact.setMiddleName(middleName.getValue());
		contact.setPhone(phone.getValue());
		
		Session sess = Sessions.getCurrent();
		sess.setAttribute("contact", contact);
		sess.setAttribute("addresses", addresses);
		sess.setAttribute("address", new UIAddress(UUID.randomUUID().toString()));
		Executions.sendRedirect("/address.zul");
	}

	@Command
	public void save() {
		try {
			ContactEjb contactEjb;
			InitialContext context = new InitialContext();
			contactEjb = (ContactEjb) context.lookup("java:module/ContactEjb");
			
			List<AddressPojo> addressesPojo = new ArrayList(addresses.size());
			for (UIAddress uiAddress : addresses) {
				addressesPojo.add(ModelUiUtils.createAddressPojo(uiAddress));
			}
			
			contactEjb.saveContact(ModelUiUtils.createContactPojo(contact), addressesPojo);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
