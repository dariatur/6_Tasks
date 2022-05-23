package by.epam.training.module6.dao.impl;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.BookType;
import by.epam.training.module6.dao.AdminDao;
import by.epam.training.module6.dao.BookDao;
import by.epam.training.module6.dao.DAOException;

public class FileAdminDao implements AdminDao{
	
	private final String BOOKS_SOURCE = "/Users/mac/workspace/module_6/home_library/src/resources/books.txt";
	private final BookDao bookDao = new FileBookDao();
	
	@Override
	public boolean add(Book book) throws DAOException {
		//title=ffff author=aaaa year=0000 type=ttttt
		boolean result = true;
		try(BufferedReader bReader = new BufferedReader(new FileReader(BOOKS_SOURCE))){
			String line = null;
			while((line=bReader.readLine())!=null) {
				String title = line.split(", ")[0].split("=")[1];
				String author = line.split(", ")[1].split("=")[1];
				int year = Integer.parseInt(line.split(", ")[2].split("=")[1]);
				BookType type = line.split(", ")[3].split("=")[1].equalsIgnoreCase("paper") ? BookType.PAPER_BOOK : BookType.EBOOK;
				Book other = new Book(title,author,year,type);
				
				if(book.equals(other)) {
					result = false;
					break;
				}
			}
		} catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		if(result) {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_SOURCE,true))){
					bw.write("title="+book.getTitle()+", author="+book.getAuthor()+", year="+book.getYearOfPublishing()+", type="+book.getType());
					bw.newLine();
			} catch(FileNotFoundException e) {
				throw new DAOException(e);
			} catch(IOException e) {
				throw new DAOException(e);
			} 
		}
		return result;
	}

	@Override
	public boolean delete(Book book) throws DAOException {
		List<Book> books = bookDao.getBooks();
		boolean result = books.remove(book);
		
		if(result) {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/mac/workspace/module_6/home_library/src/resources/books.txt"))){
				bw.write("");
				for(Book b: books) {
					bw.write("title="+b.getTitle()+", author="+b.getAuthor()+", year="+b.getYearOfPublishing()+", type="+b.getType());
					bw.newLine();
				}
				
			} catch(FileNotFoundException e) {
				throw new DAOException(e);
			} catch(IOException e) {
				throw new DAOException(e);
			} 
		}
		
		return result;
	}

	@Override
	public boolean editTitle(Book book, String newTitle) throws DAOException {
		List<Book> books = bookDao.getBooks();
		int ind = books.lastIndexOf(book);
		boolean result = false;
		
		if(ind!=-1) {
			books.get(ind).setTitle(newTitle);
			result = true;
		}
		
		if(result) {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/mac/workspace/module_6/home_library/src/resources/books.txt"))){
				bw.write("");
				for(Book b: books) {
					bw.write("title="+b.getTitle()+", author="+b.getAuthor()+", year="+b.getYearOfPublishing()+", type="+b.getType());
					bw.newLine();
				}
				
			} catch(FileNotFoundException e) {
				throw new DAOException(e);
			} catch(IOException e) {
				throw new DAOException(e);
			} 
		}
		
		return result;
	}

	@Override
	public boolean editAuthor(Book book, String newAuthor) throws DAOException {
		List<Book> books = bookDao.getBooks();
		int ind = books.lastIndexOf(book);
		boolean result = false;
		
		if(ind!=-1) {
			books.get(ind).setAuthor(newAuthor);
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean editYear(Book book, int newYear) throws DAOException {
		List<Book> books = bookDao.getBooks();
		int ind = books.lastIndexOf(book);
		boolean result = false;
		
		if(ind!=-1) {
			books.get(ind).setYearOfPublishing(newYear);;
			result = true;
		}
		
		if(result) {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/mac/workspace/module_6/home_library/src/resources/books.txt"))){
				bw.write("");
				for(Book b: books) {
					bw.write("title="+b.getTitle()+", author="+b.getAuthor()+", year="+b.getYearOfPublishing()+", type="+b.getType());
					bw.newLine();
				}
				
			} catch(FileNotFoundException e) {
				throw new DAOException(e);
			} catch(IOException e) {
				throw new DAOException(e);
			} 
		}
		
		return result;
	}

}
