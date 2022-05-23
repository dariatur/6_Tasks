package by.epam.training.module6.service;

import by.epam.training.module6.bean.Book;

public interface AdminService {
	boolean add(Book book) throws ServiceException;
	boolean delete(Book book) throws ServiceException;
	boolean editTitle(Book book,String newTitle) throws ServiceException;
	boolean editAuthor(Book book, String newAuthor) throws ServiceException;
	boolean editYear(Book book, int newYear) throws ServiceException;
	boolean sendNotification(Book book) throws ServiceException; 
}
