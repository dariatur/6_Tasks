package by.epam.training.module6.dao.impl;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import by.epam.training.module6.bean.User;
import by.epam.training.module6.bean.UserType;
import by.epam.training.module6.dao.DAOException;
import by.epam.training.module6.dao.UserDao;

public class FileUserDao implements UserDao{
	
	public FileUserDao() {};
	
	private final String USERS_SOURCE = "/Users/mac/workspace/module_6/home_library/src/resources/users.txt";
	
	@Override
	public User authorization(String login, String password) throws DAOException{
		User user = null;
		try(BufferedReader br = new BufferedReader(new FileReader(USERS_SOURCE))){
			String line = null;
			while((line=br.readLine())!=null) {
				String l = line.split(" ")[0].split("=")[1];
				String p = line.split(" ")[1].split("=")[1];
				
				if(login.equals(l)&&md5(password).equals(p)) {
					UserType type = line.split(" ")[3].split("=")[1].equalsIgnoreCase("admin")?UserType.ADMIN:UserType.USER;
					String email = line.split(" ")[2].split("=")[1];
					
					user = new User(login,password,email,type) ;
					
					break;
				}
			}
		}catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		
		return user;
	}

	@Override
	public boolean registration(User user) throws DAOException {
		//данные в формате login=xxx password=(code) type=user email=gggg
		boolean result = true;
		try(BufferedReader br = new BufferedReader(new FileReader(USERS_SOURCE))){
			String line = null;
			while((line=br.readLine())!=null) {
				String login = line.split(" ")[0].split("=")[1];
				String password = line.split(" ")[1].split("=")[1];
				String type = line.split(" ")[2].split("=")[1];
				if(login.equals(user.getLogin())&&password.equals(md5(user.getPassword()))
						||(type.equalsIgnoreCase("admin") && user.getType().toString().equalsIgnoreCase("admin"))){
					result = false;
				}
			}
		}catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		
		if(result) {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_SOURCE,true))){
				bw.write("login="+user.getLogin()+" password="+md5(user.getPassword())+" type="+user.getType()+" email="+user.getEmail());
				bw.newLine();
			} catch(FileNotFoundException e) {
				throw new DAOException(e);
			} catch(IOException e) {
				throw new DAOException(e);
			} 
		}
		
		return result;
	}
	
	public String findAdminEmail() throws DAOException{
		String adminEmail = "";
		try(BufferedReader br = new BufferedReader(new FileReader(USERS_SOURCE))){
			String line = null;
			while((line=br.readLine())!=null) {
				String type = line.split(" ")[2].split("=")[1];
				if(type.equalsIgnoreCase("admin")) {
					adminEmail = line.split(" ")[3].split("=")[1];
				}
			}
		}catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		
		return adminEmail;
	}

	@Override
	public List<User> getUsers() throws DAOException {
		List<User> users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(USERS_SOURCE))){
			String line = null;
			while((line=br.readLine())!=null) {
				String l = line.split(" ")[0].split("=")[1];
				String p = line.split(" ")[1].split("=")[1];
				UserType type = line.split(" ")[3].split("=")[1].equalsIgnoreCase("admin")?UserType.ADMIN:UserType.USER;
				String email = line.split(" ")[2].split("=")[1];
				users.add(new User(l,p,email,type));
				
			}
		}catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		return users;
	}
	
	private String md5(String password) {
		String result ;
		try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(password.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            result = no.toString(16);
            while (result.length() < 32) {
               result = "0" + result;
            }
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            result = "";
        }
		return result;
	}

	@Override
	public String getAdminEmail() throws DAOException {
		String adminEmail = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(USERS_SOURCE))){
			String line = null;
			while((line=br.readLine())!=null) {

				UserType type = line.split(" ")[3].split("=")[1].equalsIgnoreCase("admin")?UserType.ADMIN:UserType.USER;
				if(type.equals(UserType.ADMIN)) {
					adminEmail = line.split(" ")[2].split("=")[1];
				}
				
				break;
				
			}
		}catch(FileNotFoundException e) {
			throw new DAOException(e);
		} catch(IOException e) {
			throw new DAOException(e);
		} 
		
		return adminEmail;
	}
	
}
