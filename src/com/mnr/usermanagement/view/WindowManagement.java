package com.mnr.usermanagement.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowManagement {
	
	Stage primaryStage;
	
	public WindowManagement(Stage primariStage) {
		this.primaryStage = primariStage;
	}
	
	public MenuBar makeManuBar(){
		
		MenuBar menuBar = new MenuBar();
		
		Menu userMenu = new Menu("User");
		MenuItem addUser = new MenuItem("Add");
		MenuItem showUser = new MenuItem("Show");
		userMenu.getItems().addAll(addUser,showUser);
		
		Menu settingsMenu = new Menu("Settings");
		MenuItem soundSetings = new MenuItem("Sounds");
		Menu themeSettings = new Menu("Theme");
		RadioMenuItem dayTheme = new RadioMenuItem("Day");
		RadioMenuItem nightTheme = new RadioMenuItem("Night");
		ToggleGroup themeGroup = new ToggleGroup();
		dayTheme.setToggleGroup(themeGroup);
		nightTheme.setToggleGroup(themeGroup);
		dayTheme.setSelected(true);
		themeSettings.getItems().addAll(dayTheme,nightTheme);
		
		settingsMenu.getItems().addAll(soundSetings,new SeparatorMenuItem(),themeSettings);
		menuBar.getMenus().addAll(userMenu, settingsMenu);
		
		return menuBar;
		
	}
	
	public GridPane makeAddUserGrid(){
		
		GridPane userInfo = new GridPane();
		
		Text firstName = new Text("First name: ");
		TextField firstNameTF = new TextField();
		
		Text lastName = new Text("Last name: ");
		TextField lastNameTF = new TextField();
		
		Text email = new Text("email: ");
		TextField emailTF = new TextField();
		
		Text birthDate = new Text("Date of Birth: ");
		TextField birthDateTF = new TextField();
		
		Text specialInfo = new Text("Special info: ");
		TextField specialInfoTF = new TextField();
		
		Text company = new Text("Company: ");
		TextField companyTF = new TextField();
		
		Text photo = new Text("Photo: ");
		Button photoButton = new Button("Browse");
		
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
		
		return userInfo;
		
	}
	
	public VBox makeUserInfoCard(String fName, String lName,
			String companyName, int age, String email, String specInf, String photoPath){
		
		VBox infoCard = new VBox();
		
		HBox userInfo = new HBox();
		VBox mainInfo = new VBox();
		mainInfo.getChildren().addAll(
				new Text(fName + " " + lName),
				new Text("Company: " + companyName),
				new Text("Age: " + age),
				new Text("email: " + email)
				);
		ImageView userImage = new ImageView();
		userInfo.getChildren().addAll(mainInfo,userImage);
		
		infoCard.getChildren().addAll(userInfo,new Text(specInf));
		
		return infoCard;
		
	}
	
	
	public void drawAddUserFields(){
		
		primaryStage.setTitle("Add User");
		
		VBox root = new VBox();
		primaryStage.setScene(new Scene(root,400,300));
		
		root.getChildren().add(makeManuBar());
		
		//root.getChildren().add(makeAddUserGrid());
		root.getChildren().add(makeUserInfoCard("B", "m", "c", 11, "q", "d", "s"));
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((e)->{
			System.out.println("program is closing");
			Platform.exit();
			System.exit(0);
		});
		
	}

}
