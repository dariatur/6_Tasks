package by.epam.training.module6.service.impl;

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
import by.epam.training.module6.dao.DAOException;
import by.epam.training.module6.dao.DAOProvider;
import by.epam.training.module6.dao.UserDao;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.UserService;
import by.epam.training.module6.service.util.Validator;

public class UserServiceImpl implements UserService{
	private final DAOProvider provider = DAOProvider.getInstance();
	private final UserDao userDao = provider.getUserDao();
	
	@Override
	public User authorization(String login, String password) throws ServiceException {
		User user = null;
		if (!Validator.isCredentialValid(login, password)) {
			throw new ServiceException("Invalid login or password");
		} else {
			try {
				user = userDao.authorization(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		boolean result = false;
		if (!Validator.isCredentialValid(user)) {
			throw new ServiceException("Invalid login or password");
		} else {
		
			try {
				result = userDao.registration(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return result;
	}

	@Override
	public boolean sendSuggestion(User user,Book book, String password) throws ServiceException {
		String from = user.getEmail();
		String to = "";
		String host = "";
		boolean result ;
		try {
			to = userDao.getAdminEmail();
		} catch(DAOException e){
			throw new ServiceException();
		}
		
		if(Validator.isGmail(from)) {
			host = "smtp.gmail.com";
		} else if(Validator.isMailRu(from)) {
			host = "smtp.mail.ru";
		} else if(Validator.isYandex(from)) {
			 host = "smtp.yandex.ru";
		}
		
		Properties props = new Properties();
			    
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "25");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	               protected PasswordAuthentication getPasswordAuthentication() {
	                  return new PasswordAuthentication(from, password);
	   	   }
	            });

	    try {

	    	MimeMessage message = new MimeMessage(session);

	        message.setFrom(new InternetAddress(from));

	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	        message.setSubject("Просьба о добавлении книги");

	        message.setText("Пожалуйста, добавьте книгу в библиотеку. Автор: ");

	        Transport.send(message);
	        result = true;
	      } catch (MessagingException mex) {
	    	  result = false;

	      }
	
		return result;
	}

}
