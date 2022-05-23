package by.epam.training.module6.dao;

import java.util.List;

import by.epam.training.module6.bean.Book;

public interface BookDao {
	List<Book> findByTitle(String title) throws DAOException;
	List<Book> findByAuthor(String author) throws DAOException;
	List<Book> findByYear(int year) throws DAOException;
	List<Book> getBooks()throws DAOException; 
}
