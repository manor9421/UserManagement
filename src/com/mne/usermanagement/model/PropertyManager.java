package com.mne.usermanagement.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Consists methods for adding and removing properties from properties file
 * 
 * @author gdx
 *
 */
public class PropertyManager {
	
	private static final String propFile = "config.properties";
	
	public PropertyManager(){}
	
	/**
	 * select properties from file and change one of them
	 * 
	 * @param properyName - name of property for changing
	 * @param propertyValue - new value of propery
	 * 
	 */
	public void writeProperties(String properyName, String propertyValue){
		
		Properties properties = new Properties();
		String propFilePath = getClass().getClassLoader()
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
	
	/**
	 * Read property from file
	 * 
	 * @param propertyName - name of property
	 * @return value of property
	 * 
	 */
	public String readProperties(String propertyName){
		
		Properties prop = new Properties();
		
		String propFilePath = getClass().getClassLoader()
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
