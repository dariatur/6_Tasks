package by.epam.training.module6.controller;

import by.epam.training.module6.command.Command;
import by.epam.training.module6.command.CommandProvider;

public class Controller {
	
	private final CommandProvider provider = new CommandProvider();
	
	public String doAction(String request) {
		//"authorization login=aaa password=ddd"
		//"registration name=Ivan surname=Ivanov email=xxx@kk.cc"
		String commandName =  request.split(", ")[0];;
		
		Command command = provider.getCommand(commandName);;
		
		String response = command.execute(request);
		
		return response;
	}
		
}
