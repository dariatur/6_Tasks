package by.epam.training.module6.command.impl;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.AdminService;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;

public class AddAndSendNotificationsCommand implements Command{

	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		
		String title = params[1].split("=")[1];
		String author = params[2].split("=")[1];
		int year = Integer.parseInt(params[3].split("=")[1]);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		AdminService adminService = provider.getAdminService();
		
		String responce = ""; 
		
		try {
			Book book = new Book(title,author,year);
			boolean result = adminService.add(book);
			boolean check = adminService.sendNotification(book);
			if(result&&check) {
				responce = "Ok, book was added and notifications was sent.";
			} else if (result){
				responce = "Ok, book was added and notifications was not sent.";
			} else{
				responce = "Not ok, something went wrong.";
			}
		} catch (ServiceException e) {
			//log
			System.err.println(e);
			responce = "error";
		}
		return responce;
	}

}
