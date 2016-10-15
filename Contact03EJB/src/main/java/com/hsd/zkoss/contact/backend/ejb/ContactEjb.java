package com.hsd.zkoss.contact.backend.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.hsd.zkoss.contact.backend.entity.PUAddress;
import com.hsd.zkoss.contact.backend.entity.PUContact;
import com.hsd.zkoss.contact.backend.model.AddressPojo;
import com.hsd.zkoss.contact.backend.model.ContactAddressesPojo;
import com.hsd.zkoss.contact.backend.model.ContactPojo;
import com.hsd.zkoss.contact.backend.model.utils.ModelEjbUtils;

@Stateless
public class ContactEjb {

	private static Logger logger = Logger.getLogger(ContactEjb.class);

	@PersistenceUnit(unitName = "ZKOSS_HELLOWORLD_02")
	private EntityManagerFactory emf;

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void saveContact(ContactPojo contactPojo, List<AddressPojo> addressesPojo) {
		logger.info("saveContact() [contact.uuid:" + (contactPojo == null ? "ISNULL" : contactPojo.getUuid()) + "] [addressesPojo.size:"
				+ (addressesPojo == null ? "ISNULL" : addressesPojo.size()) + "]");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		PUContact contact = em.find(PUContact.class, contactPojo.getUuid());
		if (contact != null) {
			contact = em.find(PUContact.class, contactPojo.getUuid());
			contact.setUuid(contactPojo.getUuid());
			contact.setFirstName(contactPojo.getFirstName());
			contact.setLastName(contactPojo.getLastName());
			contact.setMiddleName(contactPojo.getMiddleName());
			contact.setPhone(contactPojo.getPhone());
		} else {
			contact = new PUContact();
			contact.setUuid(contactPojo.getUuid());
			contact.setFirstName(contactPojo.getFirstName());
			contact.setLastName(contactPojo.getLastName());
			contact.setMiddleName(contactPojo.getMiddleName());
			contact.setPhone(contactPojo.getPhone());
			em.persist(contact);
		}
		
		Query query = em.createNamedQuery("Address.deleteByContact");
		query.setParameter("contact", contact);
		try {
			query.executeUpdate();
		} catch (PersistenceException pe) {
		}

		for (AddressPojo addressPojo : addressesPojo) {
			PUAddress address = em.find(PUAddress.class, addressPojo.getUuid());
			if (address != null) {
				address.setUuid(addressPojo.getUuid());
				address.setPostalCode(addressPojo.getPostalCode());
				address.setCity(addressPojo.getCity());
				address.setStreet(addressPojo.getStreet());
				address.setBuilding(addressPojo.getBuilding());
				address.setApartment(addressPojo.getApartment());
				address.setContact(contact);
			} else {
				address = new PUAddress();
				address.setUuid(addressPojo.getUuid());
				address.setPostalCode(addressPojo.getPostalCode());
				address.setCity(addressPojo.getCity());
				address.setStreet(addressPojo.getStreet());
				address.setBuilding(addressPojo.getBuilding());
				address.setApartment(addressPojo.getApartment());
				address.setContact(contact);
				em.persist(address);
			}
		}
		em.getTransaction().commit();
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public ContactAddressesPojo findContact(String lastName) {
		logger.info("findContact() [lastName:" + lastName + "]");
		
		ContactAddressesPojo contactAddressesPojo = new ContactAddressesPojo();
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<PUContact> queryDevice = em.createNamedQuery("Contact.findByLastName", PUContact.class);
		queryDevice.setParameter("lastName", lastName);
		PUContact puContact = null;
		try {
			puContact = queryDevice.getSingleResult();
			contactAddressesPojo.setContact(ModelEjbUtils.createContactPojo(puContact));
			for (PUAddress puAddress : puContact.getAddresses()) {
				contactAddressesPojo.getAddresses().add(ModelEjbUtils.createAddressPojo(puAddress));
			}
		} catch (NoResultException nre) {
			logger.info("NoResultException_01 ");
			em.getTransaction().rollback();
			return null;
		}
		
		em.getTransaction().commit();
		
		return contactAddressesPojo;
	}

}
