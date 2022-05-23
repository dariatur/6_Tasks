package by.epam.training.module6.dao;

import by.epam.training.module6.dao.impl.FileAdminDao;
import by.epam.training.module6.dao.impl.FileBookDao;
import by.epam.training.module6.dao.impl.FileUserDao;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private DAOProvider() {};
	
	public static DAOProvider getInstance() {
		return instance;
	}
	
	private UserDao userDao = new FileUserDao();
	private BookDao bookDao = new FileBookDao();
	private AdminDao adminDao = new FileAdminDao();
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}
		
}
