package by.epam.training.module6.service;

import by.epam.training.module6.service.impl.AdminServiceImpl;
import by.epam.training.module6.service.impl.BookServiceImpl;
import by.epam.training.module6.service.impl.UserServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	private UserService userService = new UserServiceImpl();
	private BookService bookService = new BookServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	
	private ServiceProvider() {};
	
	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public BookService getBookService() {
		return bookService;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
	
}
