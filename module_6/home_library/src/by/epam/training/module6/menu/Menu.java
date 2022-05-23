package by.epam.training.module6.menu;

import java.util.Scanner;

import by.epam.training.module6.bean.User;
import by.epam.training.module6.bean.UserType;
import by.epam.training.module6.controller.Controller;

public class Menu {
	private static final Menu instance  = new Menu();
	private final Controller controller = new Controller();
	
	private Menu() {};
	
	public static Menu getInstance() {
		return instance;
	}
	
	public void startMenu() {
		System.out.println("Home library application");
		System.out.println("1. Authorization\n"
				+"2. Registration\n"+"3. Exit.");
		System.out.println("Enter a number from 1 to 3: ");
		while(true) {
			int number = enterNumber();
			User user = null;
			if(number<1||number>3) {
				System.out.println("Wrong number, please try again.");
			}
			switch(number) {
			case 1: user = authorization();
					if(user!=null&&user.getType().equals(UserType.USER)) {
						userActions(user);
					} else if(user!=null&&user.getType().equals(UserType.ADMIN)) {
						adminActions();
					}
					break;
			case 2: user = registration();
					if(user!=null&&user.getType().equals(UserType.USER)) {
						userActions(user);
					} else if(user!=null&&user.getType().equals(UserType.ADMIN)) {
						adminActions();
					}
					break;
			}
			if(number == 3) {
				break;
			}
			
		}
		
	}
	
	private int enterNumber() {
		int number = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(">>");
		while(!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("Invalid value, please try again");
			System.out.print(">>");
		}
		number = sc.nextInt();
		return number;
	}
	
	private String enter() {
		String result;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(">>");
		
		result = sc.nextLine();
		return result;
	}
	
	private User authorization() {
		System.out.println("Enter login: ");
		String login = enter();
		System.out.println("Enter password: ");
		String password = enter();
		User user = null;
		String responce = controller.doAction(new String("authorization, login="+login+", password="+password));
		if(responce.split(" ")[0].equalsIgnoreCase("ok")) {
			String email = responce.split(" ")[3].split("=")[1];
			UserType type = responce.split(" ")[4].split("=")[1].equalsIgnoreCase("admin") ? UserType.ADMIN : UserType.USER;
			
			user = new User(login, password, email, type);
		} else if(responce.split(" ")[0].equalsIgnoreCase("not")){
			System.out.println("No such user");
		}
		
		return user;
	}
	
	private User registration() {
		System.out.println("Enter login: ");
		String login = enter();
		
		System.out.println("Enter password: ");
		String password = enter();
		
		System.out.println("Enter email: ");
		String email = enter();
		
		System.out.println("Are you user or admin?");
		String type = enter();
		
		String responce = controller.doAction("registration, login="+login+", password="+password+", email="+email+", type="+type);
		User user = null;
		
		if(responce.split(" ")[0].equalsIgnoreCase("ok")) {
			user = new User(login,password,email,type.equalsIgnoreCase("user")?UserType.USER:UserType.ADMIN);
			System.out.println("Registration was successful");
		} else if(responce.split(" ")[0].equalsIgnoreCase("not")){
			System.out.println("Something went wrong");
		}
		
		return user;
	}
	
	private void userActions(User user) {
		System.out.println("1. Find books by title.\n"
						+"2. Find books by author.\n"
						+"3. Find books by year.\n"
						+"4. Send an email to the administrator with a suggestion to add a book.\n"
						+"5. Get catalog.\n"
						+"6. Log out.");
		System.out.println("Enter a number from 1 to 6: ");
		while(true) {
			int number = enterNumber();
			if(number<1||number>6) {
				System.out.println("Wrong number, please try again.");
			}
			switch(number) {
			case 1: findBooksByTitle();
					break;
			case 2: findBooksByAuthor();
					break;
			case 3: findBooksByYear();
					break;
			case 4: sendSuggestion(user);
					break;
			case 5: getCatalog();;
					break;
			}
			if(number == 6) {
				break;
			}
		}
	}
	
	private void adminActions() {
		System.out.println("1. Add new book and send notifications.\n"
				+"2. Delete book.\n"
				+"3. Edit book(year of publishing).\n"
				+"4. Edit book(author).\n"
				+"5. Edit book(title).\n"
				+"6. Log out.");
		System.out.println("Enter a number from 1 to 6: ");
		while(true) {
			int number = enterNumber();
			if(number<1||number>6) {
				System.out.println("Wrong number, please try again.");
			}
			switch(number) {
			case 1: addBook();
					break;
			case 2: deleteBook();
					break;
			case 3: editYear();
					break;
			case 4: editAuthor();
					break;
			case 5:	editTitle();
					break;
			}
			
			if(number == 6) {
				break;
			}
		}
		
	}
	
	private void addBook() {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		String responce = controller.doAction("addAndSend, title="+title+", author="+author+", year="+year);
		
		System.out.println(responce);
	}
	
	private void deleteBook() {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		String responce = controller.doAction("delete, title="+title+", author="+author+", year="+year);
		
		System.out.println(responce);
	}
	
	private void getCatalog() {
		String responce = controller.doAction("catalog");
		
		System.out.println(responce);
	}
	
	private void editYear() {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		System.out.println("Enter new year");
		int newYear = enterNumber();
		
		String responce = controller.doAction("delete, title="+title+", author="+author+", year="+year+", newYear="+newYear);
		
		System.out.println(responce);
	}
	
	private void editAuthor() {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		System.out.println("Enter new author");
		String newAuthor = enter();
		
		String responce = controller.doAction("delete, title="+title+", author="+author+", year="+year+", newAuthor="+newAuthor);
		
		System.out.println(responce);
	}
	
	private void editTitle() {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		System.out.println("Enter new author");
		String newTitle = enter();
		
		String responce = controller.doAction("delete, title="+title+", author="+author+", year="+year+", newTitle="+newTitle);
		
		System.out.println(responce);
	}
	
	private void findBooksByTitle() {
		System.out.println("Enter the title");
		String title = enter();
		
		String responce = controller.doAction("findByTitle, title="+title);
		
		System.out.println(responce);
		
	}
	
	private void findBooksByAuthor() {
		System.out.println("Enter the author");
		String author = enter();
		
		String responce = controller.doAction("findByAuthor, author="+author);
		
		System.out.println(responce);
		
	}
	
	private void findBooksByYear() {
		System.out.println("Enter the year");
		int year = enterNumber();
		
		String responce = controller.doAction("findByYear, year="+year);
		
		System.out.println(responce);
		
	}
	
	private void sendSuggestion(User user) {
		System.out.println("Enter the title");
		String title = enter();
		
		System.out.println("Enter the author");
		String author = enter();
		
		System.out.println("Enter the year");
		int year = enterNumber();
		
		System.out.println("Enter your email password");
		String password = enter();
		
		String responce = controller.doAction("suggestion, login="+ user.getLogin()+", userPassword="+user.getPassword()
										+", email="+user.getEmail()+", title="+title+", author="+author+", year="+year+", password="+password);
		
		System.out.println(responce);
				
	}
	
	
}
