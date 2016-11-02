package com.mnr.usermanagement.controller;

import java.io.File;

import com.mnr.usermanagement.db.DBDataManger;
import com.mnr.usermanagement.impls.User;
import com.mnr.usermanagement.model.ImageUtils;
import com.mnr.usermanagement.model.Model;
import com.mnr.usermanagement.model.PropertiesManager;
import com.mnr.usermanagement.model.RegexValidate;
import com.mnr.usermanagement.view.WindowManagement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewController {
	
	public static EventHandler<ActionEvent> settingsListener(){
		EventHandler<ActionEvent> settingsMenuHandler = (ae)->{
			RadioMenuItem target = (RadioMenuItem) ae.getTarget();
			String name = ( target ).getText();
			switch (name) {
				case "Sound":
					if(target.isSelected()){
						PropertiesManager.writeProperties("sound", "off");
					}
					break;
				case "Day":
					//if(!dayTheme.isSelected())
						PropertiesManager.writeProperties("theme", "day");
					break;
				case "Night":
					//if(!nightTheme.isSelected())
						PropertiesManager.writeProperties("theme", "night");
					break;
				default:
					break;
			}
			
		};
		
		return settingsMenuHandler;
		
	}
	
	public static EventHandler<ActionEvent> itemListener(BorderPane root){
		
		EventHandler<ActionEvent> userMenuHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ae) {
				String name = ( (MenuItem) ae.getTarget() ).getText();
				
				switch(name){
					case "Add":
						WindowManagement.drawAddUserGrid();
						break;
					case "Show":
						// take data
						
						root.setCenter(WindowManagement.showUserCards(DBDataManger.selectAllusers()));
						break;
					default:
						break;
				}// end of switch
			}
			
		};
		
		return userMenuHandler;
		
	}

	public static EventHandler<ActionEvent> confirmButtonListener(String fName, String lName, String email,
			String company, String specInf, String photoPath, String birthDate) {
		System.out.println("before: "+fName + lName + email+company+specInf+photoPath+birthDate);
		
		EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//validate
				User user = Model.createUser(fName, lName, email, company, specInf, photoPath, birthDate);
				
				if(user != null){

					if( DBDataManger.insertUserData(user) ){
						System.out.println("Added to db");
						
						WindowManagement.drawAddUserGrid();
						
					}
					
				}
			}
		};
		
		return ae;
		
	}
	
	
	
	public static EventHandler<ActionEvent> imageButtonListener(){
		EventHandler<ActionEvent> ae = (e)->{
			
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Br");
			File file = chooser.showOpenDialog(new Stage());
			((Button) e.getTarget()).setText(ImageUtils.savePicture(file.getPath()));
			
		};
		return ae;
	}
	
}
