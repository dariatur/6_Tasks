package by.epam.training.module6.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.training.module6.command.impl.AddAndSendNotificationsCommand;
import by.epam.training.module6.command.impl.AuthorizationCommand;
import by.epam.training.module6.command.impl.DeleteCommand;
import by.epam.training.module6.command.impl.EditAuthorCommand;
import by.epam.training.module6.command.impl.EditTitleCommand;
import by.epam.training.module6.command.impl.EditYearCommand;
import by.epam.training.module6.command.impl.FindByAuthorCommand;
import by.epam.training.module6.command.impl.FindByTitleCommand;
import by.epam.training.module6.command.impl.FindByYearCommand;
import by.epam.training.module6.command.impl.GetBooksCommand;
import by.epam.training.module6.command.impl.RegistrationCommand;
import by.epam.training.module6.command.impl.SuggestionCommand;

public class CommandProvider {
	private Map<String, Command> commands;
	
	public CommandProvider() {
		commands = new HashMap<String, Command>();
		commands.put("authorization", new AuthorizationCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("suggestion", new SuggestionCommand());
		
		commands.put("findByTitle", new FindByTitleCommand());
		commands.put("findByAuthor", new FindByAuthorCommand());
		commands.put("findByYear", new FindByYearCommand());
		commands.put("catalog", new GetBooksCommand());
		
		commands.put("addAndSend", new AddAndSendNotificationsCommand());
		commands.put("delete", new DeleteCommand());
		commands.put("editYear", new EditYearCommand());
		commands.put("editAuthor", new EditAuthorCommand());
		commands.put("editTitle", new EditTitleCommand( ));
		

	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
	
	
}
