package by.epam.training.module6.bean;

public enum UserType {
	USER("User"), ADMIN("Admin");
	
	private String type;
	
	UserType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
