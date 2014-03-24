package com.zan.diary.events.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDetails {
	
	private List<String> userDiaries; 
	
	  public UserDetails() {
		  id = -1;
	  }

	  public UserDetails(long id) {
	    this.id = id;
	  }
	
	
	public List<String> getUserDiaries() {
		return userDiaries;
	}
	public void setUserDiaries(List<String> userDiaries) {
		this.userDiaries = userDiaries;
	}

	private long id;

	public void setid(long id) { this.id = id;}
	public long getid() {  return id;  }

	private String name;

	public void setName(String name) { this.name = name;}
	public String getName() {  return name;  }

	private String pass;

	public void setPass(String pass) { this.pass = pass;}
	public String getPass() {  return pass;  }	  

	private String code;

	public void setCode(String code) { this.code = code;}
	public String getCode() {  return code;  }	

	private String loc;

	public void setLoc(String loc) { this.loc = loc;}
	public String getLoc() {  return loc;  } 

	private Date dateTimeOfCreation;

	public void setDateTimeOfCreation(Date dateTimeOfCreation) {
		this.dateTimeOfCreation = dateTimeOfCreation;
	}

	public Date getDateTimeOfCreation() {
		return dateTimeOfCreation;
	}

}
