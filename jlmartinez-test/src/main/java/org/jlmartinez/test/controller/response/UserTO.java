package org.jlmartinez.test.controller.response;

public class UserTO{
	
	public UserTO() {

	}
	
	public UserTO(String name, String email, String birthDate, AddressTO address) {
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.address = address;
	}
	
	private Integer id;
	private String name;
	private String email;
	private String birthDate;
	private AddressTO address;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public AddressTO getAddress() {
		return address;
	}
	public void setAddress(AddressTO address) {
		this.address = address;
	}
	
}
