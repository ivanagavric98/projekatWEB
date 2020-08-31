package model;

public class Address {
	public String id;		//jedinstveni identifikator
	public String street;
	public String number;
	public String city;
	public String postalCode;
	
	public Address() {}
	
	public Address(String id, String street, String number, String city, String postalCode) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}

