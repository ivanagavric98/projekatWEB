package model;

public class Amenities {
	public long id;
	public String name;
	public boolean active;
	
	public Amenities() {
		active = true;
	}
	
	public Amenities(long id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

