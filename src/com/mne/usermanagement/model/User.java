package com.mne.usermanagement.model;

/**
 * Class for all fields of users data
 * 
 * @author gdx
 *
 */
public class User {

	private String fName;
	private String lName;
	private String email;
	private String company;
	private String specInf;
	private String photoPath;
	private String birthDate;
	
	public User(String fName, String lName, String email, String company,
			String specInf, String photoPath, String birthDate) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.company = company;
		this.specInf = specInf;
		this.photoPath = photoPath;
		this.birthDate = birthDate;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSpecInf() {
		return specInf;
	}

	public void setSpecInf(String specInf) {
		this.specInf = specInf;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
}
