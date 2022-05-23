package by.epam.training.module6.command.impl;

import by.epam.training.module6.bean.User;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;
import by.epam.training.module6.service.UserService;

public class AuthorizationCommand implements Command{

	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		
		String login = params[1].split("=")[1];
		String password = params[2].split("=")[1];
		
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		
		String responce;
		try {
			User user = userService.authorization(login, password);
			
			if(user!=null) {
				responce = "Ok it's login="+user.getLogin()+" password="+user.getPassword()
				+" email="+user.getEmail()+" type="+user.getType().toString().toLowerCase();
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
