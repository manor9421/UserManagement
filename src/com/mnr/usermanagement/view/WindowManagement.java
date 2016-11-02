package com.mnr.usermanagement.view;

import java.util.ArrayList;

import com.mnr.usermanagement.controller.ViewController;
import com.mnr.usermanagement.db.DBDataManger;
import com.mnr.usermanagement.impls.User;
import com.mnr.usermanagement.model.ImageUtils;
import com.mnr.usermanagement.model.Model;
import com.mnr.usermanagement.model.PropertiesManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowManagement {
	
	Stage primaryStage;
	static BorderPane root;
	
	/**
	 * create window, and add menu bar
	 * @param primariStage
	 */
	public WindowManagement(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("Add User");
		root = new BorderPane();
		root.setTop(makeManuBar());//add menu bar
		// make scene
		primaryStage.setScene(new Scene(root,400,300));
		// show scene
		primaryStage.show();
		
		// close actions
		primaryStage.setOnCloseRequest((e)->{
			System.out.println("program is closing...");
			Platform.exit();
			System.exit(0);
		});
		
	}
	
	/**
	 * 
	 * @return menu bar with all views and actions
	 */
	public MenuBar makeManuBar(){
		
		MenuBar menuBar = new MenuBar();
		
		Menu userMenu = makeUserMenu();
		Menu settingsMenu = makeSettingsMenu();
		
		menuBar.getMenus().addAll(userMenu, settingsMenu);
		
		return menuBar;
		
	}
	private Menu makeUserMenu(){
		/** user menu */
		Menu userMenu = new Menu("User");
		MenuItem addUser = new MenuItem("Add");
		MenuItem showUser = new MenuItem("Show");//
		
		// add items to user menu
		userMenu.getItems().addAll(addUser,showUser);
		
		// add handlers
		addUser.setOnAction(ViewController.itemListener(root));
		showUser.setOnAction(ViewController.itemListener(root));
		
		return userMenu;
		
	}
	private Menu makeSettingsMenu(){
		// settings menu
		Menu settingsMenu = new Menu("Settings");
		
		RadioMenuItem soundItem = new RadioMenuItem("Sounds");
		Menu themeSettings = new Menu("Theme");
		RadioMenuItem dayTheme = new RadioMenuItem("Day");
		RadioMenuItem nightTheme = new RadioMenuItem("Night");
		ToggleGroup themeGroup = new ToggleGroup();
		dayTheme.setToggleGroup(themeGroup);
		nightTheme.setToggleGroup(themeGroup);
		
		String sound = PropertiesManager.readProperties("sound");
		if(sound == null || sound.equals("on")){
			soundItem.setSelected(true);
		}
		
		String theme = PropertiesManager.readProperties("theme");
		
		if( theme == null || theme.equals("day")){
			dayTheme.setSelected(true);
		}else{
			nightTheme.setSelected(true);
		}
		
		themeSettings.getItems().addAll(dayTheme,nightTheme);
		
		settingsMenu.getItems().addAll(soundItem,new SeparatorMenuItem(),themeSettings);

		soundItem.setOnAction(ViewController.settingsListener());
		dayTheme.setOnAction(ViewController.settingsListener());
		nightTheme.setOnAction(ViewController.settingsListener());
		
		return settingsMenu;
		
	}
	
	

	public static void drawAddUserGrid(){
		
		GridPane userInfo = new GridPane();
		
		Text firstName = new Text("First name: ");
		TextField firstNameTF = new TextField();
		
		Text lastName = new Text("Last name: ");
		TextField lastNameTF = new TextField();
		
		Text email = new Text("email: ");
		TextField emailTF = new TextField();
		emailTF.setPromptText("name@site.com");
		
		Text birthDate = new Text("Date of Birth: ");
		TextField birthDateTF = new TextField();
		birthDateTF.setPromptText("01/01/1900");
		
		Text specialInfo = new Text("Special info: ");
		TextField specialInfoTF = new TextField();
		
		Text company = new Text("Company: ");
		TextField companyTF = new TextField();
		
		Text photo = new Text("Photo: ");
		Button photoButton = new Button("Browse");
		photoButton.setMaxWidth(200);
		photoButton.setOnAction(ViewController.imageButtonListener());
		
		Button confirm = new Button("Ok");
		/*confirm.setOnAction(ViewController.confirmButtonListener(firstNameTF.getText(), lastNameTF.getText(),
				emailTF.getText(), companyTF.getText(), specialInfoTF.getText(),
				photoButton.getText(), birthDateTF.getText()));
		*/
		confirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("before: "+firstNameTF.getText()+ lastNameTF.getText()+
						emailTF.getText()+ companyTF.getText()+ specialInfoTF.getText()+
						photoButton.getText()+ birthDateTF.getText());
				//validate
				User user = Model.createUser(firstNameTF.getText(), lastNameTF.getText(),
						emailTF.getText(), companyTF.getText(), specialInfoTF.getText(),
						photoButton.getText(), birthDateTF.getText());
				
				if(user != null){

//					if( DBDataManger.insertUserData(user) ){
//						System.out.println("Added to db");
//						
//						WindowManagement.drawAddUserGrid();
//						
//					}
					
				}
			}
		});
		userInfo.add(firstName, 0, 0);
		userInfo.add(firstNameTF, 1, 0);
		userInfo.add(lastName, 0, 1);
		userInfo.add(lastNameTF, 1, 1);
		userInfo.add(email, 0, 2);
		userInfo.add(emailTF, 1, 2);
		userInfo.add(birthDate, 0, 3);
		userInfo.add(birthDateTF, 1, 3);
		userInfo.add(specialInfo, 0, 4);
		userInfo.add(specialInfoTF, 1, 4);
		userInfo.add(company, 0, 5);
		userInfo.add(companyTF, 1, 5);
		userInfo.add(photo, 0, 6);
		userInfo.add(photoButton, 1, 6);
		userInfo.add(confirm, 1, 7);
		
		root.setCenter(userInfo);
		
	}

	
	public static ScrollPane showUserCards(ArrayList<User> users){
		
		ScrollPane content = new ScrollPane();
		content.setPrefSize(280, 100);
		
		VBox userCardsBox = new VBox();
		
		for (User user: users) {
			userCardsBox.getChildren().add(makeUserInfoCard(user));
		}
		
		content.setContent(userCardsBox);
		
		return content;
		
	}
	
	private static VBox makeUserInfoCard(User user){
		
		VBox infoCard = new VBox();
		
		HBox userInfo = new HBox();
		VBox mainInfo = new VBox();
		mainInfo.getChildren().addAll(
				new Text(user.getfName() + " " + user.getlName()),
				new Text("Company: " + user.getCompany()),
				new Text("Age: " + user.getBirthDate()),
				new Text("email: " + user.getEmail())
				);
		
		String pathname = "userImages" + "/" + user.getPhotoPath();
		
		Image userImg = ImageUtils.cropImage(pathname);
		
		ImageView userIV = null;
		if( userImg != null ){
			userIV = new ImageView(userImg);
		}else{
			userIV = new ImageView();
			System.out.println("image is null");
		}
		
		userInfo.getChildren().addAll(mainInfo,userIV);
		
		infoCard.getChildren().addAll(userInfo,new Text(user.getSpecInf()));
		
		return infoCard;
		
	}

	
	
	

}
