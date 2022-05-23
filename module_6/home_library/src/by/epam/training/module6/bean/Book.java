package by.epam.training.module6.bean;

public class Book {
	private String title;
	private String author;
	private int yearOfPublishing;
	private BookType type;
	
	public Book() {};
	
	public Book(String title, String author, int yearOfPublishing) {
		this.title = title;
		this.author = author;
		this.yearOfPublishing = yearOfPublishing;
		this.type = BookType.PAPER_BOOK;
	}
	
	public Book(String title, String author, int yearOfPublishing, BookType type) {
		this.title = title;
		this.author = author;
		this.yearOfPublishing = yearOfPublishing;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(int yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + yearOfPublishing;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		
		return this.author.equals(other.author)&&this.title.equals(title)
				&&this.yearOfPublishing==other.yearOfPublishing&&this.type.equals(type);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", yearOfPublishing=" + yearOfPublishing + ", type="
				+ type + "]";
	}
	

}
