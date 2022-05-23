package by.epam.training.module6.command.impl;

import java.util.List;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.command.Command;
import by.epam.training.module6.service.BookService;
import by.epam.training.module6.service.ServiceException;
import by.epam.training.module6.service.ServiceProvider;

public class FindByAuthorCommand implements Command{
	
	@Override
	public String execute(String request) {
		String[] params;
		params = request.split(", ");
		
		String author = params[1].split("=")[1];
		
		ServiceProvider provider = ServiceProvider.getInstance();
		BookService bookService = provider.getBookService();
		
		String responce = ""; 
		try {
			List<Book> books = bookService.findByAuthor(author);
			
			if(!books.isEmpty()) {
				for(Book book : books) {
					responce += "Title: "+book.getTitle()+", author: "+book.getAuthor()
					+", year of publishing: "+book.getYearOfPublishing()+", type of book: "+book.getType()+"\n";
				}
			} else {
				responce = "Not ok, there are no books of such author";
			}
		} catch (ServiceException e) {
			//log
			System.err.println(e);
			responce = "error";
		}
		
		return responce;
	}
}
