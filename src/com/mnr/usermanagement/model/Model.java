package com.mnr.usermanagement.model;

import com.mnr.usermanagement.impls.User;

public class Model {

	public static User createUser(String fName, String lName, String email,
			String company, String specInf, String photoPath, String birthDate){
		
		long birthTime = RegexValidate.dateToMillis(birthDate);
		
		System.out.println("look: " + fName + lName + email + company + specInf+photoPath+birthDate+birthTime);
		//User user = new User(fName, lName, email, company, specInf, photoPath, birthTime);
		//return user;
			return null;
		
		//System.out.println("model err");
		//return null;
		
	}
	
	private static synchronized boolean checkString(String s){
		
		if(s.isEmpty() || s.equals("")){
			return false;
		}else{
			return true;
		}

	}
	
	
	
	
}
