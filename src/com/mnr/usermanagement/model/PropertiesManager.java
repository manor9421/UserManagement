package com.mnr.usermanagement.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class PropertiesManager {
	
	private static String propFile = "config.properties";
	
	private PropertiesManager(){}

	public static void writeProperties(String properyName, String propertyValue){
		
		Properties properties = new Properties();
		String propFilePath = PropertiesManager.class.getClassLoader()
				.getResource("config.properties").getFile();
		
		InputStream is = null;
		OutputStream os = null;
		try{
			
			is = new FileInputStream(propFilePath);
			
			properties.load(is);
			
			is.close();
			
			os = new FileOutputStream(propFilePath);
			
			properties.setProperty(properyName, propertyValue);

			// save properties to project resource folder
			properties.store(os, null);
			
			os.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readProperties(String propertyName){
		
		Properties prop = new Properties();
		
		String propFilePath = PropertiesManager.class.getClassLoader()
				.getResource(propFile).getFile();//getClass().
		
		try(
				InputStream is = new FileInputStream(propFilePath);
		){
			// load a properties file
			prop.load(is);
			System.out.println(propFilePath);
			String propValue = prop.getProperty(propertyName);
			System.out.println(propertyName + " value: " + propValue);
			return propValue;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
