package com.mnr.usermanagement.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class PropertiesManager {
	
	private static PropertiesManager instance;
	
	private static String propFile = "config.properties";
	
	private PropertiesManager(){}
	
	// singleton
	public static synchronized PropertiesManager getInstance() {
		
		if(instance == null){
			instance = new PropertiesManager();
		}
		
		return instance;
		
	}

	public static void writeProperties(String properyName, String propertyValue){
		
		Properties properties = new Properties();
		String propFilePath = PropertiesManager.class.getClassLoader()
				.getResource(propFile).getFile();
		
		try(
			OutputStream os = new FileOutputStream(propFilePath);
		){
			properties.setProperty(properyName, propertyValue);
			
			// save properties to project resource folder
			properties.store(os, null);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OK");
		
	}
	
	public static String readProperties(String propertyName){
		
		Properties prop = new Properties();
		
		String propFilePath = PropertiesManager.class.getClassLoader()
				.getResource(propFile).getFile();//getClass().
		try(
			InputStream is = new FileInputStream(propFilePath)
			//InputStream is = PropertiesManager.class.getClassLoader().getResourceAsStream(propFilePath);
		){
			
			// load a properties file
			prop.load(is);
			
			if(prop.contains(propertyName)){
				String propValue = prop.getProperty(propertyName);
				System.out.println("property " + propertyName + " exists");
				return propValue;
				
			}
			System.out.println("no property:" + propertyName);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
