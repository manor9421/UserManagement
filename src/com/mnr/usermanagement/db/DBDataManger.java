package com.mnr.usermanagement.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDataManger {
	
	/**
	 * Insert new user data to SQLite database through transaction.
	 * It inform us in console about start of executing and commit.
	 * 
	 * @param name - name of new user
	 * @param email - email of new user
	 * @param age - age of new user
	 * @return true if user adeed succesfully to database
	 * @throws ClassNotFoundException 
	 */
	public static boolean inserSqliteData(String fName,
			String lName,String email,String company,String specInf,String photoPath,long birthDate) throws ClassNotFoundException{
		
		// if email is bad
		if(ValidateParams.validEmail(email)==false && lName.isEmpty() && birthDate <= 0){
			return false;
		}
		
		String sql = "INSERT INTO `users`(`f_name`,`l_name`,`email`,`company`,`spec_inf`,`photo_path`,`birth_date`) VALUES(?,?,?,?,?,?,?)";
		
		try(
			Connection connection = DBUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
		){
			connection.setAutoCommit(false);
			
			stmt.setString(1, fName);
			stmt.setString(2, lName);
			stmt.setString(3, email);
			stmt.setString(4, company);
			stmt.setString(5, specInf);
			stmt.setString(6, photoPath);
			stmt.setLong(7, birthDate);
			
			int result = stmt.executeUpdate();
			
			if(result == 1){
				System.out.println("Wait for commit...");
			}
			
			connection.commit();
			
			System.out.println("Commit. Added to Sqlite");

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
	}
	
	
	public static boolean insertUserData(User user) throws ClassNotFoundException{
		
		String sql = "INSERT INTO `users`(`f_name`,`l_name`,`email`,`company`,`spec_inf`,`photo_path`,`birth_date`) VALUES(?,?,?,?,?,?,?)";
		
		try(
			Connection connection = DBUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
		){
			connection.setAutoCommit(false);
			
			stmt.setString(1, user.getfName());
			stmt.setString(2, user.getlName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getCompany());
			stmt.setString(5, user.getSpecInf());
			stmt.setString(6, user.getPhotoPath());
			stmt.setLong(7, user.getBirthDate());
			
			int result = stmt.executeUpdate();
			
			if(result == 1){
				System.out.println("Wait for commit...");
			}
			
			connection.commit();
			
			System.out.println("Commit. Added to Sqlite");

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
		
	}
	
	
	/**
	 * Select data of user from SQLite database by name
	 * and write every user's email and age to console.
	 * Then write total count of found users to console
	 * 
	 * @param name - name of selecting user
	 * @return true if all data selected successfully
	 * @throws ClassNotFoundException 
	 */
	public static boolean selectSqliteData(String name) throws ClassNotFoundException{
		
		String sql = "SELECT * FROM users WHERE f_name=?";
		
		ResultSet rSet = null;
		try(
			Connection connection = DBUtil.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
		){
			
			stmt.setString(1, name);
			
			rSet = stmt.executeQuery();
			int num = 0;
			
			while(rSet.next()){
				num++;
				
				String userInfo = 
						"name: " + rSet.getString("f_name") + " " + rSet.getString("l_name") + " "+
				rSet.getString("email") + " " + rSet.getString("company") + " " + rSet.getString("spec_inf") + " " + 
				rSet.getString("photo_path") + " " + rSet.getString("birth_date");
				System.out.println(userInfo);
				
			}
			
			System.out.println("Total names: " + num);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
