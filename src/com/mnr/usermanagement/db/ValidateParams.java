package com.mnr.usermanagement.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateParams {

	public static final Pattern EMAIL_REGEX = Pattern.compile(
			"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			Pattern.CASE_INSENSITIVE );
	
	public static final Pattern DATE_REGEX = Pattern.compile(
			"^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$",
			Pattern.CASE_INSENSITIVE );
	
	public static boolean validEmail(String email){
		Matcher matcher = EMAIL_REGEX.matcher(email);
		return matcher.find();
	}
	
	public static boolean validDate(String date){
		Matcher matcher = DATE_REGEX.matcher(date);
		return matcher.find();
	}
	
	public static long makeLong(String date){
		if(validDate(date)){
			try {
				
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    Date date1;
				date1 = formatter.parse(date);
				long dateInLong = date1.getTime();
			    
				return dateInLong;
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    
		}
		
		return 0L;
	}
	
}
