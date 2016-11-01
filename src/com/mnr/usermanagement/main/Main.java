package com.mnr.usermanagement.main;

import com.mnr.usermanagement.view.WindowManagement;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		WindowManagement wm = new WindowManagement(primaryStage);
		
		wm.drawAddUserFields();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}