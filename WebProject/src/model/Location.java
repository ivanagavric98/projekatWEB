package model;

public class Location {
	public String id;		//jedinstveni identifikator
	public String latitude;
	public String longitude;
	public Address address;
	
	public Location() {}
	
	public Location(String id, String latitude, String longitude, Address address) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
