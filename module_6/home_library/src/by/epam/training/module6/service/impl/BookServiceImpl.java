package by.epam.training.module6.service.impl;

import java.util.List;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.dao.BookDao;
import by.epam.training.module6.dao.DAOException;
import by.epam.training.module6.dao.DAOProvider;
import by.epam.training.module6.service.BookService;
import by.epam.training.module6.service.ServiceException;

public class BookServiceImpl implements BookService{
	private final DAOProvider provider = DAOProvider.getInstance();
	private final BookDao bookDao = provider.getBookDao();
	
	@Override
	public List<Book> findByTitle(String title) throws ServiceException {
		List<Book> result;
		if(title==null||title.isBlank()||title.isEmpty()) {
			throw new ServiceException("Wrong title of book");
		} else {
			try {
				result = bookDao.findByTitle(title);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	@Override
	public List<Book> findByAuthor(String author) throws ServiceException {
		List<Book> result;
		if(author==null||author.isBlank()||author.isEmpty()) {
			throw new ServiceException("Wrong author");
		} else {
			try {
				result = bookDao.findByAuthor(author);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return result;
	}

	@Override
	public List<Book> findByYear(int year) throws ServiceException {
		List<Book> result;
		if(year<1900||year>2022) {
			throw new ServiceException("Wrong year");
		} else {
			try {
				result = bookDao.findByYear(year);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return result;
	}
	
	@Override
	public List<Book> getBooks() throws ServiceException {
		List<Book> result;
		try {
			result = bookDao.getBooks();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

}
