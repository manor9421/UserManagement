package com.mnr.usermanagement.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.mnr.usermanagement.db.DBDataManger;
import com.mnr.usermanagement.db.PropertiesManager;
import com.mnr.usermanagement.db.User;
import com.mnr.usermanagement.view.WindowManagement;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		WindowManagement wm = new WindowManagement(primaryStage);
		String aString = wm.savePicture("/home/gdx/Desktop/voprosi.png");
		
		System.out.println(aString);
		
		//wm.drawAllUserFields();
		
		
		
		/*DBDataManger.inserSqliteData("billy","Sax","r@r.com","Fujitsu","like bananas",
				"/imf",1241241424);
		*/
		
		/*User newU = new User("Misha", "Koryashkin", "k@m.m", "ntech", "hate mushrooms", "/s.s", 132323);
		
		DBDataManger.insertUserData(newU);
		
		DBDataManger.selectSqliteData("Misha");
		
		*/
		
		/*PropertiesManager pm = new PropertiesManager();
		//pm.writeProperties("database", "users");
		
		System.out.println(pm.readProperties("database"));
		*/
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
