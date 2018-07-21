package org.jlmartinez.test.utils;

import java.time.LocalDateTime;

import org.jlmartinez.test.controller.response.AddressTO;
import org.jlmartinez.test.controller.response.UserTO;
import org.jlmartinez.test.model.Address;
import org.jlmartinez.test.model.User;

public class TestUtils {

	public static UserTO createUserTO(String name, String email, LocalDateTime birthDate,
			AddressTO address) {
		//TODO convert localtime to string
		return new UserTO(name, email,"", address);
	}
	
	public static AddressTO createAddressTO(String city, String country, String state, 
			String street, String zip) {
		return new AddressTO(city, country, state, street, zip);
	}
	
	public static User createMockUser(String name, String email, LocalDateTime birthDate,
			Address address) {
		User mockUser = new User();
		mockUser.setName(name);
		mockUser.setEmail(email);
		mockUser.setBirthDate(birthDate);
    	mockUser.setAddress(address);
		return mockUser;
	}
	
	public static Address createMockAddress(String city, String country, String state, 
			String street, String zip) {
		Address mockAddress = new Address();
		mockAddress.setCity(city);
		mockAddress.setCountry(country);
		mockAddress.setState(state);
		mockAddress.setStreet(street);
		mockAddress.setZip(zip);
		return mockAddress;
	}
}
