package by.epam.training.module6.service;

import java.util.List;

import by.epam.training.module6.bean.Book;

public interface BookService {
	List<Book> findByTitle(String title) throws ServiceException;
	List<Book> findByAuthor(String author) throws ServiceException;
	List<Book> findByYear(int year) throws ServiceException;
	List<Book> getBooks() throws ServiceException;
	
}
