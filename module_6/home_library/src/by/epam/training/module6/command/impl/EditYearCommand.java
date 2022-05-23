package by.epam.training.module6.command.impl;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.AdminService;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;

public class EditYearCommand implements Command{
	
	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		
		String title = params[1].split("=")[1];
		String author = params[2].split("=")[1];
		int year = Integer.parseInt(params[3].split("=")[1]);
		int newYear = Integer.parseInt(params[4].split("=")[1]);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		AdminService adminService = provider.getAdminService();
		
		String responce = ""; 
		
		try {
			boolean result = adminService.editYear(new Book(title,author,year),newYear);
			if(result) {
				responce = "Ok, year was edited";
			} else {
				responce = "Not ok";
			}
		} catch (ServiceException e) {
			//log
			System.err.println(e);
			responce = "error";
		}
		return responce;
	}

}
