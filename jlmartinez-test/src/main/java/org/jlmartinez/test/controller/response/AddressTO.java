package org.jlmartinez.test.controller.response;

public class AddressTO {

	public AddressTO() {

	}
	
	public AddressTO(String city, String country, String state, 
			String street, String zip) {
		
		this.city = city;
		this.country = country;
		this.state = state;
		this.street = street;
		this.zip = zip;
	}
	
	private Integer id; 
	private String city;
	private String country;
	private String state;
	private String street; 
	private String zip;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

}
