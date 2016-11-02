package com.mnr.usermanagement.main;

import com.mnr.usermanagement.db.DBDataManger;
import com.mnr.usermanagement.db.User;
import com.mnr.usermanagement.view.WindowManagement;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		WindowManagement wm = new WindowManagement(primaryStage);
		
		wm.drawAllUserFields();
		
		
		
		/*DBDataManger.inserSqliteData("billy","Sax","r@r.com","Fujitsu","like bananas",
				"/imf",1241241424);
		*/
		
		/*User newU = new User("Misha", "Koryashkin", "k@m.m", "ntech", "hate mushrooms", "/s.s", 132323);
		
		DBDataManger.insertUserData(newU);
		
		DBDataManger.selectSqliteData("Misha");
		
		*/
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
