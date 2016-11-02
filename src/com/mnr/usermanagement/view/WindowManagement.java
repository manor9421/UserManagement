package com.mnr.usermanagement.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import com.mnr.usermanagement.db.DBDataManger;
import com.mnr.usermanagement.db.PropertiesManager;
import com.mnr.usermanagement.db.User;
import com.mnr.usermanagement.model.ImageManipulator;
import com.mnr.usermanagement.model.RegexValidate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WindowManagement {
	
	Stage primaryStage;
	BorderPane root;
	
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
		
		EventHandler<ActionEvent> userMenuHandler = (ae)->{
			String name = ( (MenuItem) ae.getTarget() ).getText();
			switch(name){
				case "Add":
					root.setCenter(makeAddUserGrid());
					break;
				case "Show":
					root.setCenter(makeUserCards());
					break;
				default:
					break;
			}// end of switch
		};
		
		// add handlers
		addUser.setOnAction(userMenuHandler);
		showUser.setOnAction(userMenuHandler);
		
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

		EventHandler<ActionEvent> settingsMenuHandler = (ae)->{
			String name = ( (RadioMenuItem) ae.getTarget() ).getText();
			switch (name) {
				case "Sound":
					if(soundItem.isSelected()){
						//soundItem.setSelected(false);
						PropertiesManager.writeProperties("sound", "off");
					}
					break;
				case "Day":
					if(!dayTheme.isSelected())
						PropertiesManager.writeProperties("theme", "day");
					break;
				case "Night":
					if(!nightTheme.isSelected())
						PropertiesManager.writeProperties("theme", "night");
					break;
				default:
					break;
			}
			
		};
		
		soundItem.setOnAction(settingsMenuHandler);
		dayTheme.setOnAction(settingsMenuHandler);
		nightTheme.setOnAction(settingsMenuHandler);
		
		return settingsMenu;
		
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
		photoButton.setMaxWidth(200);
		photoButton.setOnAction((e)->{
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Br");
			File file = chooser.showOpenDialog(new Stage());
			photoButton.setText(savePicture(file.getPath()));
			
		});
		
		Button confirm = new Button("Ok");
		confirm.setOnAction((e)->{
			
			User user = new User(firstNameTF.getText(), lastNameTF.getText(),
					emailTF.getText(), companyTF.getText(), specialInfoTF.getText(),
					photoButton.getText(), RegexValidate.dateToMillis(birthDateTF.getText()));
			
			if( DBDataManger.insertUserData(user) ){
				
				System.out.println("Added to db");
				
				firstNameTF.setText("");
				lastNameTF.setText("");
				emailTF.setText("");
				companyTF.setText("");
				specialInfoTF.setText("");
				photoButton.setText("");
				birthDateTF.setText("");
				
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
		
		return userInfo;
		
	}

	
	
	
	
	public VBox makeUserInfoCard(User user){
		
		VBox infoCard = new VBox();
		
		HBox userInfo = new HBox();
		VBox mainInfo = new VBox();
		mainInfo.getChildren().addAll(
				new Text(user.getfName() + " " + user.getlName()),
				new Text("Company: " + user.getCompany()),
				new Text("Age: " + user.getBirthDate()),
				new Text("email: " + user.getEmail())
				);
		
		//String pathname = "userImages/s1.jpg";
		
		String pathname = "userImages" + "/" + user.getPhotoPath();
		
		Image userImg = ImageManipulator.cropImage(pathname);
		
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
	
	
	public VBox makeUserCards(){
		
		VBox content = new VBox();
		
		for (User user: DBDataManger.selectAllusers()) {
			
			content.getChildren().add(makeUserInfoCard(user));
			
		}
		
		return content;
		
	}
	

	public String savePicture(String path){

		try{
			File imgFolder = new File( getClass().getClassLoader().getResource("userImages").getFile() );
			if(imgFolder.isDirectory()){
				System.out.println("dir:" + imgFolder);
				
				File sourceImg = new File(path);
				String extension = "";
				int i = sourceImg.toString().lastIndexOf('.');
				if (i > 0) {
				    extension = sourceImg.toString().substring(i+1);
				}
				//BufferedImage bi = new BufferedImage(772,492,BufferedImage.TYPE_INT_ARGB);
				BufferedImage bi = ImageIO.read(sourceImg);
				
				String newName = createNewName() + "." + extension;
				File newImageFile = new File( imgFolder + "/" + newName );
				newImageFile.createNewFile();
				
				ImageIO.write(bi, extension, newImageFile);
				
				System.out.println("file copied");
				
				return newName;
				
			}else{
				System.out.println("not dir: "+imgFolder);
			}
		}catch (Exception e) {
			System.out.println("exc");
		}
		
		return null;
		
	}
	
	/**
	 * create random name for image(without extension)
	 * @return
	 */
	public String createNewName(){
		Random rand = new Random();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<6;i++){
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		
		sb.append(System.currentTimeMillis());
		
		return sb.toString();
		
	}

}
