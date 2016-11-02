package com.mnr.usermanagement.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.mnr.usermanagement.main.Main;

public class PropertiesManager {// TODO make singleton
	
	private static String propFile = "config.properties";

	public void writeProperties(String properyName, String propertyValue){
		
		Properties properties = new Properties();
		String propFilePath = getClass().getClassLoader()
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
	
	public String readProperties(String propertyName){
		
		Properties prop = new Properties();
		
		String propFilePath = getClass().getClassLoader()
				.getResource(propFile).getFile();
		try(
			InputStream is = new FileInputStream(propFilePath)
			//InputStream is = PropertiesManager.class.getClassLoader().getResourceAsStream(propFilePath);
		){
			
			// load a properties file
			prop.load(is);
			
			if(prop.contains(propertyName)){
				String propValue = prop.getProperty(propertyName);
				System.out.println("yeeee");
				return propValue;
				
			}
			System.out.println("noooo");
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
