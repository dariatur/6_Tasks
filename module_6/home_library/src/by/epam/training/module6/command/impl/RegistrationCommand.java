package by.epam.training.module6.command.impl;

import by.epam.training.module6.bean.User;
import by.epam.training.module6.bean.UserType;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;
import by.epam.training.module6.service.UserService;

public class RegistrationCommand implements Command{

	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		
		String login = params[1].split("=")[1];
		String password = params[2].split("=")[1];
		String email = params[3].split("=")[1];
		UserType type = params[2].split("=")[1].equalsIgnoreCase("admin") ? UserType.ADMIN : UserType.USER;
		
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		
		String responce;
		try {
			boolean result = userService.registration(new User(login, password, email, type));
			
			if(result) {
				responce = "Ok registration was successful";
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
