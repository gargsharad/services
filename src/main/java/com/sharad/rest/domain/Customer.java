package com.sharad.rest.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
public class Customer {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public Integer getId() {
		return id;
	}

	@XmlAttribute(required=false)
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	@XmlElement(name="first-name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@XmlElement(name="last-name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	@XmlElement
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	@XmlElement
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}
}
