package by.epam.training.module6.bean;

public enum BookType {
	EBOOK("Ebook"), PAPER_BOOK("Paper");
	
	private String type;
	
	BookType(String type) {
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
