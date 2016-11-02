package com.mnr.usermanagement.model;

public class Model {

	public static boolean validateUserData(String fName, String lName, String email,
			String company, String specInf, String photoPath, String birthDate){
		
		long birthTime = RegexValidate.dateToMillis(birthDate);
		
		if(checkString(fName) && checkString(lName)	&& RegexValidate.validEmail(email)
				&& checkString(company) && checkString(specInf)
				&& checkString(photoPath) && birthTime > 0
		){
			
			return true;
			
		}
		System.out.println("model err");
		return false;
		
	}
	
	private static boolean checkString(String s){
		
		if(s.isEmpty() || s.equals("")){
			return false;
		}else{
			return true;
		}

	}
	
	
	
	
}
