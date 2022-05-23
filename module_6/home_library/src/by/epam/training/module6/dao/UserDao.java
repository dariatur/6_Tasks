package by.epam.training.module6.dao;

import java.util.List;

import by.epam.training.module6.bean.User;

public interface UserDao {
	User authorization(String login, String password) throws DAOException;
	boolean registration(User user) throws DAOException;
	List<User> getUsers() throws DAOException;
	String getAdminEmail()throws DAOException; 
}
