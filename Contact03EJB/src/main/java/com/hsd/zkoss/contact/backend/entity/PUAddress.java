package com.hsd.zkoss.contact.backend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity()
@Table(name = "address")
@NamedQueries({ @NamedQuery(name = "Address.deleteByContact", query = "DELETE FROM PUAddress a WHERE a.contact = :contact") })
public class PUAddress implements Serializable {

	private String uuid;
	private PUContact contact;
	private String postalCode;
	private String city;
	private String street;
	private String building;
	private String apartment;
	
	public PUAddress() {
		super();
	}

	public PUAddress(String uuid) {
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

	@ManyToOne(fetch = FetchType.LAZY) /*fetch = FetchType.LAZY */
	@JoinColumn(name = "contact_uuid")
	public PUContact getContact() {
		return contact;
	}

	public void setContact(PUContact contact) {
		this.contact = contact;
	}

	@Column(name = "postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "street")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "building")
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	@Column(name = "apartment")
	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

}
