package by.epam.training.module6.dao;

import by.epam.training.module6.bean.Book;

public interface AdminDao {
	boolean add(Book book) throws DAOException;
	boolean delete(Book book) throws DAOException;
	boolean editTitle(Book book,String newTitle) throws DAOException;
	boolean editAuthor(Book book, String newAuthor) throws DAOException;
	boolean editYear(Book book, int newYear) throws DAOException;
}
