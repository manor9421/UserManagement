package com.mne.usermanagement.view;


import java.util.ArrayList;

import com.mne.usermanagement.controller.Controller;
import com.mne.usermanagement.model.DBUtil;
import com.mne.usermanagement.model.ImageUtils;
import com.mne.usermanagement.model.User;

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

public class WindowManager {

	public Stage primaryStage;
	public BorderPane root;
	public Controller controller;

	/**
	 * create new controller, create new scene with label and menu bar, and show it
	 * 
	 * @param primaryStage - stage which will be filled with new stage
	 * 
	 * @see Stage
	 * @see Controller
	 */
	public WindowManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.controller = new Controller(this);
		
		primaryStage.setTitle("User Management");
		root = new BorderPane();
		root.setTop(makeManuBar());
		primaryStage.setScene(new Scene(root,400,300));
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((e)->{
			System.out.println("program is closing...");
			Platform.exit();
			System.exit(0);
		});
		
	}
	
	/**
	 * Create menu bar
	 * 
	 * @return menu bar with all items
	 * @see MenuBar
	 */
	public MenuBar makeManuBar(){
		
		MenuBar menuBar = new MenuBar();
		
		Menu userMenu = makeUserMenu();
		Menu settingsMenu = makeSettingsMenu();
		
		menuBar.getMenus().addAll(userMenu, settingsMenu);
		
		return menuBar;
		
	}
	
	/**
	 * create 'User' menu with items for changing scene for add and show users
	 * 
	 * @return menu for user manipulations
	 * @see Menu
	 * @see MenuItem
	 */
	private Menu makeUserMenu(){
		/** user menu */
		Menu userMenu = new Menu("User");
		MenuItem addUser = new MenuItem("Add");
		MenuItem showUser = new MenuItem("Show");//
		
		// add items to user menu
		userMenu.getItems().addAll(addUser,showUser);
		
		// add handlers
		addUser.setOnAction(controller.itemListener());
		showUser.setOnAction(controller.itemListener());
		
		return userMenu;
		
	}
	
	/**
	 * Create 'settings' menu for settings manipulations.
	 * Depends on Controller
	 * @return menu with items for manipulating with properties
	 * @see Controller
	 */
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
		
		
		themeSettings.getItems().addAll(dayTheme,nightTheme);
		
		settingsMenu.getItems().addAll(soundItem,new SeparatorMenuItem(),themeSettings);

		soundItem.setOnAction(controller.settingsListener());
		dayTheme.setOnAction(controller.settingsListener());
		nightTheme.setOnAction(controller.settingsListener());
		
		return settingsMenu;
		
	}
	
	/**
	 * Draw all fields for adding new user to existing scene
	 */
	public void drawAddUserGrid(){
		
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
		photoButton.setOnAction(controller.imageButtonListener());
		
		Button confirm = new Button("Ok");
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				User user = new User(firstNameTF.getText(),lastNameTF.getText(),emailTF.getText(),companyTF.getText(),
						specialInfoTF.getText(),photoButton.getText(),birthDateTF.getText());
				
				if(user != null){

					DBUtil dbu = new DBUtil();
					
					if( dbu.insertUserData(user) ){
						System.out.println("Added to db");
						
						drawAddUserGrid();
						
					}
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
	
	/**
	 * Draw all users information to the scene.
	 * Call methods for generate {@link VBox}'es with user information
	 * 
	 * @param users - array list of users
	 */
	public void showUserCards(ArrayList<User> users){
		
		ScrollPane content = new ScrollPane();
		content.setPrefSize(280, 100);
		
		VBox userCardsBox = new VBox();
		
		for (User user: users) {
			userCardsBox.getChildren().add(makeUserInfoCard(user));
		}
		
		content.setContent(userCardsBox);
		
		root.setCenter(content);
		
	}
	
	/**
	 * generate VBox with user information
	 * 
	 * @param user - user which info will be added to VBox
	 * @return VBox with user information
	 * @see VBox
	 * @see User
	 */
	private VBox makeUserInfoCard(User user){
		
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
		
		ImageUtils iu = new ImageUtils();
		Image userImg = iu.cropImage(pathname);
		
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
