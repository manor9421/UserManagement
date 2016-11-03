package com.mne.usermanagement.main;

import com.mne.usermanagement.view.WindowManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * extends {@link Application} and contains methods for launching apllication
 * 
 * @author gdx
 * @since 1.1
 */
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		new WindowManager(primaryStage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
