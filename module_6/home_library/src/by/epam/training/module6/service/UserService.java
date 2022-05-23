package by.epam.training.module6.service;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.User;

public interface UserService {
	User authorization(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	boolean sendSuggestion(User user,Book book, String password) throws ServiceException;
}
