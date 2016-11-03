package com.mne.usermanagement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * consists all information for database connection
 * 
 * @author gdx
 *
 */
public class DBUtil {
	
	static final String sqliteUrl = "jdbc:sqlite::resource:users.db";//in resource folder
	
	/**
	 * create connection with database
	 * 
	 * @return SQLite JDBC connection for the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		Class.forName("org.sqlite.JDBC");
		
		return DriverManager.getConnection(sqliteUrl);
		
	}
	
	/**
	 * insert user data to the database with transaction
	 * 
	 * @param user - User with data for inserting to database
	 * @return true after data successfully committed
	 * 
	 * @see User
	 */
	public boolean insertUserData(User user){
		
		String sql = "INSERT INTO `users`(`f_name`,`l_name`,`email`,`company`,`spec_inf`,`photo_path`,`birth_date`) VALUES(?,?,?,?,?,?,?)";
		
		try(
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
		){
			
			connection.setAutoCommit(false);
			
			stmt.setString(1, user.getfName());
			stmt.setString(2, user.getlName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getCompany());
			stmt.setString(5, user.getSpecInf());
			stmt.setString(6, user.getPhotoPath());
			stmt.setString(7, user.getBirthDate());
			
			int result = stmt.executeUpdate();
			
			if(result == 1){
				System.out.println("Wait for commit...");
			}
			
			connection.commit();
			
			System.out.println("Commit. Added to Sqlite");

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
			
		return false;
		
	}

	/**
	 * select data of all Users in database
	 * 
	 * @return {@link ArrayList} of selected Users or null if database is empty
	 * 
	 * @see User
	 */
	public ArrayList<User> selectAllusers(){
		
		String sql = "SELECT * FROM users";
		
		ResultSet rSet = null;
		try(
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
		){
			
			rSet = stmt.executeQuery();
			
			ArrayList<User> selectedUsers = new ArrayList<>();
			
			while(rSet.next()){
				
				selectedUsers.add(new User(rSet.getString("f_name"),  rSet.getString("l_name"),
						rSet.getString("email"), rSet.getString("company"), rSet.getString("spec_inf"),
						rSet.getString("photo_path"), rSet.getString("birth_date")));
				
			}
			
			return selectedUsers;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
}
