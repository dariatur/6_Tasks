package by.epam.training.module6.command.impl;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.User;
import by.epam.training.module6.bean.UserType;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;
import by.epam.training.module6.service.UserService;

public class SuggestionCommand implements Command{

	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		//suggestion login=dd userPassword=jjj email=dd title=dd author=ff year=jfkf password=fkfk
		String login = params[1].split("=")[1];
		String userPassword = params[2].split("=")[1];
		String email = params[3].split("=")[1];
		String title = params[4].split("=")[1];
		String author =  params[5].split("=")[1];
		int year = Integer.parseInt( params[6].split("=")[1]);
		String password =  params[7].split("=")[1];
		
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		
		String responce;
		try {
			boolean result = userService.sendSuggestion(new User(login,userPassword,email,UserType.USER), new Book(title, author, year), password);
			if(result) {
				responce = "Ok, your suggestion was sent.";
			} else {
				responce = "Not ok, your suggestion was not sent.";
			}
		} catch (ServiceException e) {
			//log
			System.err.println(e);
			responce = "error";
		}
		return responce;
	}
	
}
