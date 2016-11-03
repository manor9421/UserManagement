package com.mne.usermanagement.controller;

import java.io.File;

import com.mne.usermanagement.model.DBUtil;
import com.mne.usermanagement.model.ImageUtils;
import com.mne.usermanagement.model.PropertyManager;
import com.mne.usermanagement.view.WindowManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	
	WindowManager wm;
	PropertyManager pm;
	
	public Controller(WindowManager wm) {
		
		this.wm = wm;
		this.pm = new PropertyManager();
		
	}
	
	/**
	 * create event listener for 'settings' menu items
	 * 
	 * @return {@link EventHandler} for settings menu item
	 */
	public EventHandler<ActionEvent> settingsListener(){
		EventHandler<ActionEvent> settingsMenuHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				RadioMenuItem target = (RadioMenuItem) ae.getTarget();
				String name = ( target ).getText();
				switch (name) {
					case "Sound":
						//if(target.isSelected()){
							pm.writeProperties("sound", "off");
						//}
						break;
					case "Day":
						//if(!target.isSelected())
							pm.writeProperties("theme", "day");
						break;
					case "Night":
						//if(!target.isSelected())
							pm.writeProperties("theme", "night");
						break;
					default:
						break;
				}// end of switch
			}//end of handle
		};
		return settingsMenuHandler;
	}
	
	/**
	 * create action listener for 'user' menu items
	 * 
	 * @return {@link EventHandler} for 'user' menu items
	 */
	public EventHandler<ActionEvent> itemListener(){
		
		EventHandler<ActionEvent> userMenuHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ae) {
				String name = ( (MenuItem) ae.getTarget() ).getText();
				
				switch(name){
					case "Add":
						wm.drawAddUserGrid();
						break;
					case "Show":
						// take data
						DBUtil dbu = new DBUtil();
						
						wm.showUserCards( dbu.selectAllusers() );
						break;
					default:
						break;
				}// end of switch
			}
			
		};
		
		return userMenuHandler;
		
	}

	/**
	 * Create action listener for 'browse image' button.
	 * Call save picture method and change button text to new image name
	 * 
	 * @return {@link EventHandler} for 'browse image' button
	 */
	public EventHandler<ActionEvent> imageButtonListener(){
		EventHandler<ActionEvent> ae = (e)->{
			
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Select image");
			File file = chooser.showOpenDialog(new Stage());
			ImageUtils iu = new ImageUtils();
			((Button) e.getTarget()).setText(iu.savePicture(file.getPath()));
			
		};
		return ae;
	}
	
	/**
	 * Find user picture and create ImageView with it.
	 * 
	 * @param name - name of user picture
	 * @return {@link ImageView} with user picture(if exists) or empty one
	 */
	public ImageView getUserImage(String name){
		
		String path = "userImages" + "/" + name;
		
		ImageUtils iu = new ImageUtils();
		Image userImg = iu.cropImage(path);
		
		ImageView userIV = null;
		if( userImg != null ){
			userIV = new ImageView(userImg);
		}else{
			userIV = new ImageView();
			System.out.println("image is null");
		}
		
		return userIV;
		
	}

}
