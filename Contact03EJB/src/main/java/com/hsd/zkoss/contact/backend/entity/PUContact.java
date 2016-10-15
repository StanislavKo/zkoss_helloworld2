package com.hsd.zkoss.contact.backend.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "contact")
@NamedQueries({
	@NamedQuery(name = "Contact.findByLastName", query = "SELECT c FROM PUContact c WHERE c.lastName = :lastName") })
public class PUContact implements Serializable {

	private String uuid;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phone;
	private Set<PUAddress> addresses;
	
	public PUContact() {
		super();
	}

	public PUContact(String uuid) {
		super();
		this.uuid = uuid;
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy="contact", fetch=FetchType.LAZY)
	public Set<PUAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<PUAddress> addresses) {
		this.addresses = addresses;
	}

}
