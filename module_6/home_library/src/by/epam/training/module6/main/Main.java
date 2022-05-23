package by.epam.training.module6.main;

import by.epam.training.module6.menu.Menu;

public class Main {

	
	public static void main(String[] args){

//		User user1 = new User("Dasha","Tur", "darida","sanddki12","dasha@mail.ru",UserType.ADMIN);
//		User user2 = new User("Dasha","Tur", "fada","sa","dasha@mail.ru",UserType.USER);
//		turbina computer8761

		Menu menu = Menu.getInstance();
		menu.startMenu();
	}

}
