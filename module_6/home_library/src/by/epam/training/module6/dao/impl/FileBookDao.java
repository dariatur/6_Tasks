package by.epam.training.module6.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.BookType;
import by.epam.training.module6.dao.BookDao;
import by.epam.training.module6.dao.DAOException;

public class FileBookDao implements BookDao{
	
	private final String BOOKS_SOURCE = "/Users/mac/workspace/module_6/home_library/src/resources/books.txt";
	
	@Override
	public List<Book> findByTitle(String title) throws DAOException {
		List<Book> resultBooks = new ArrayList<Book>();
		try(BufferedReader bReader = new BufferedReader(new FileReader(BOOKS_SOURCE))){
			String line = null;
			while((line=bReader.readLine())!=null) {
				String bookTitle = line.split(", ")[0].split("=")[1];
				String author = line.split(", ")[1].split("=")[1];
				int year = Integer.parseInt(line.split(", ")[2].split("=")[1]);
				BookType type = line.split(", ")[3].split("=")[1].equalsIgnoreCase("paper") ? BookType.PAPER_BOOK : BookType.EBOOK;
				Book book = new Book(bookTitle,author,year,type);
				
				if(title.equalsIgnoreCase(bookTitle)) {
					resultBooks.add(book);
				}
			}
		} catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		return resultBooks;
	}

	@Override
	public List<Book> findByAuthor(String author) throws DAOException {
		List<Book> resultBooks = new ArrayList<Book>();
		try(BufferedReader bReader = new BufferedReader(new FileReader(BOOKS_SOURCE))){
			String line = null;
			while((line=bReader.readLine())!=null) {
				String title = line.split(", ")[0].split("=")[1];
				String bookAuthor = line.split(", ")[1].split("=")[1];
				int year = Integer.parseInt(line.split(", ")[2].split("=")[1]);
				BookType type = line.split(", ")[3].split("=")[1].equalsIgnoreCase("paper") ? BookType.PAPER_BOOK : BookType.EBOOK;
				Book book = new Book(title,bookAuthor,year,type);
				Pattern authorPattern = Pattern.compile(author.toLowerCase());
				Matcher matcher = authorPattern.matcher(bookAuthor.toLowerCase());
				if(bookAuthor.equalsIgnoreCase(author)||matcher.find()) {
					resultBooks.add(book);
				}
			}
		} catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		return resultBooks;
	}

	@Override
	public List<Book> findByYear(int year) throws DAOException {
		List<Book> resultBooks = new ArrayList<Book>();
		try(BufferedReader bReader = new BufferedReader(new FileReader(BOOKS_SOURCE))){
			String line = null;
			while((line=bReader.readLine())!=null) {
				String title = line.split(", ")[0].split("=")[1];
				String author = line.split(", ")[1].split("=")[1];
				int yearOfPublishing = Integer.parseInt(line.split(", ")[2].split("=")[1]);
				BookType type = line.split(", ")[3].split("=")[1].equalsIgnoreCase("paper") ? BookType.PAPER_BOOK : BookType.EBOOK;
				Book book = new Book(title,author,yearOfPublishing,type);
				
				if(year == yearOfPublishing) {
					resultBooks.add(book);
				}
			}
		} catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		return resultBooks;
	}

	@Override
	public List<Book> getBooks() throws DAOException {
		List<Book> books = new ArrayList<Book>();
		try(BufferedReader bReader = new BufferedReader(new FileReader(BOOKS_SOURCE))){
			String line = null;
			while((line=bReader.readLine())!=null) {
				String title = line.split(", ")[0].split("=")[1];
				String author = line.split(", ")[1].split("=")[1];
				int yearOfPublishing = Integer.parseInt(line.split(", ")[2].split("=")[1]);
				BookType type = line.split(", ")[3].split("=")[1].equalsIgnoreCase("paper") ? BookType.PAPER_BOOK : BookType.EBOOK;
				Book book = new Book(title,author,yearOfPublishing,type);
				books.add(book);
			}
		} catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		
		return books;
	}

}
