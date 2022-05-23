package by.epam.training.module6.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.module6.bean.Book;
import by.epam.training.module6.bean.User;
import by.epam.training.module6.dao.BookDao;

public class Validator {
	
	public static boolean isCredentialValid(String login, String password) {
		if(login.isEmpty() || password.isEmpty()) {
			return false;
		}
		if(login == null || password == null) {
			return false;
		}
		Pattern patternLogin = Pattern.compile("[a-z]{3,10}[0-9]{0,3}");
		Pattern patternPassword = Pattern.compile("[a-zA-Z|0-9]{5,10}");
		Matcher matcher1 = patternLogin.matcher(login);
		Matcher matcher2 = patternPassword.matcher(password);
		
		return matcher1.find()&&matcher2.find();
	}
	
	public static boolean isCredentialValid(User user) {
		if(user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
			return false;
		}
		if(user.getLogin() == null || user.getPassword() == null || user.getEmail() == null || user.getType() == null) {
			return false;
		}
		Pattern patternLogin = Pattern.compile("[a-z]{3,10}[0-9]{0,3}");
		Pattern patternPassword = Pattern.compile("[a-zA-Z0-9]{5,10}");
		Pattern gmail = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(gmail\\.com){1}");
		Pattern mailRu = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(mail\\.ru){1}");
		Pattern yandex = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(yandex\\.){1}(ru|by|com){1}");
		
		Matcher matcher1 = patternLogin.matcher(user.getLogin());
		Matcher matcher2 = patternPassword.matcher(user.getPassword());
		Matcher matcherGmail = gmail.matcher(user.getEmail());
		Matcher matcherMail = mailRu.matcher(user.getEmail());
		Matcher matcherYandex = yandex.matcher(user.getEmail());
		
		return matcher1.find()&&matcher2.find()&&(matcherGmail.find()||matcherMail.find()||matcherYandex.find());
	}
	
	public static boolean isGmail(String email) {
		Pattern gmail = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(gmail\\.com){1}");
		Matcher matcherGmail = gmail.matcher(email);
		
		return matcherGmail.find();
	}
	
	public static boolean isYandex(String email) {
		Pattern yandex = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(yandex\\.){1}(ru|by|com){1}");
		Matcher matcher = yandex.matcher(email);
		
		return matcher.find();
	}
	
	public static boolean isMailRu(String email) {
		Pattern mailRu = Pattern.compile("[a-z\\.0-9_-]+[a-z0-9]+@(mail\\.ru){1}");
		Matcher matcher = mailRu.matcher(email);
		
		return matcher.find();
	}
	
	public static boolean isCorrectBook(Book book) {
		
		if(book == null) {
			return false;
		} 
		if(book.getTitle()==null||book.getTitle().isEmpty()||book.getTitle().isBlank()) {
			return false;
		}
		if(book.getAuthor()==null||book.getAuthor().isBlank()||book.getAuthor().isEmpty()) {
			return false;
		}
		if(book.getYearOfPublishing()<1900||book.getYearOfPublishing()>2022) {
			return false;
		}
		if(book.getType()==null) {
			return false;
		}
		
		return true;
	}
	
}
