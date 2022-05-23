package by.epam.training.module6.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.User;
import by.epam.training.module6.dao.AdminDao;
import by.epam.training.module6.dao.DAOException;
import by.epam.training.module6.dao.DAOProvider;
import by.epam.training.module6.dao.UserDao;
import by.epam.training.module6.service.AdminService;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.util.Validator;

public class AdminServiceImpl implements AdminService{
	private final DAOProvider provider = DAOProvider.getInstance();
	private final AdminDao adminDao = provider.getAdminDao();
	private final UserDao userDao = provider.getUserDao();
	
	public AdminServiceImpl() {};
	
	@Override
	public boolean add(Book book) throws ServiceException {
		boolean result;
		if(!Validator.isCorrectBook(book)) {
			throw new ServiceException("Invalid parameters of book");
		} else {
			try {
				result = adminDao.add(book);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	@Override
	public boolean delete(Book book) throws ServiceException {
		boolean result;
		if(!Validator.isCorrectBook(book)) {
			throw new ServiceException("Invalid parameters of book");
		} else {
			try {
				result = adminDao.delete(book);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	@Override
	public boolean editTitle(Book book, String newTitle) throws ServiceException {
		boolean result;
		if(!Validator.isCorrectBook(book)||newTitle==null||newTitle.isBlank()||newTitle.isEmpty()) {
			throw new ServiceException("Invalid parameters of book or new title");
		} else {
			try {
				result = adminDao.editTitle(book,newTitle);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	@Override
	public boolean editAuthor(Book book, String newAuthor) throws ServiceException {
			boolean result;
			if(!Validator.isCorrectBook(book)||newAuthor==null||newAuthor.isBlank()||newAuthor.isEmpty()) {
				throw new ServiceException("Invalid parameters of book or new author");
			} else {
				try {
					result = adminDao.editAuthor(book,newAuthor);
				} catch (DAOException e) {
					throw new ServiceException(e);
				}
			}
			return result;
	}

	@Override
	public boolean editYear(Book book, int newYear) throws ServiceException {
			boolean result;
			if(!Validator.isCorrectBook(book)||newYear>2022||newYear<1900) {
				throw new ServiceException("Invalid parameters of book or year");
			} else {
				try {
					result = adminDao.editYear(book,newYear);
				} catch (DAOException e) {
					throw new ServiceException(e);
				}
			}
			return result;
	}

	@Override
	public boolean sendNotification(Book book) throws ServiceException {
		boolean result = false;
		List<User> users; 
		try{
			users = userDao.getUsers();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	
		String from = "homelibrary39@gmail.com";
		String password = "module5task6";
	    String host = "smtp.gmail.com";
	  
	    Properties props = new Properties();

	    // Настроить почтовый сервер
	    
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "25");

	    // Получение объекта Session по умолчанию
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	               protected PasswordAuthentication getPasswordAuthentication() {
	                  return new PasswordAuthentication(from, password);
	   	   }
	            });

	    try {
	        // Создание объекта MimeMessage по умолчанию
	    	MimeMessage message = new MimeMessage(session);
	    	message.setSubject("Добавлена новая книга");
		    message.setText("Здравствуйсте. Хотим уведомить, что в библиотеку была добавлена новая книга. Название: "
		        				+book.getTitle()+", автор: "+book.getAuthor());
	        // Установить От: поле заголовка
	        message.setFrom(new InternetAddress(from));

	        for(User user : users) {
	        	 message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
	        	 Transport.send(message);
	        }
	        
	        result = true;

	      } catch (MessagingException mex) {
	    	  result = false;
	      }
	
		return result;
	}

}
