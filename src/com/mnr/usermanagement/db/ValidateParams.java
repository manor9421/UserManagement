package com.mnr.usermanagement.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateParams {

	public static final Pattern EMAIL_REGEX = Pattern.compile(
			"^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			Pattern.CASE_INSENSITIVE );
	
	public static boolean validEmail(String email){
		Matcher matcher = EMAIL_REGEX.matcher(email);
		return matcher.find();
	}
	
}
