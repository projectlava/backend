package com.zan.diary.rest.domain;

import com.zan.diary.events.user.UserDetails;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.ResourceSupport;

public class User extends ResourceSupport implements Serializable {

  /**
	 * 
	 */
	private Date dateTimeOfCreation;
	private List<String> userDiaries; 
	private UUID id;
	private String name;
	private String loc;
	private String pass;
	private String code;

	public void setDateTimeOfCreation(Date dateTimeOfCreation) {this.dateTimeOfCreation = dateTimeOfCreation;	}
	public Date getDateTimeOfCreation() {	return dateTimeOfCreation;	}
	public void setid(UUID id) { this.id = id;}
	public UUID getid() {  return id;  }
	public void setName(String name) { this.name = name;}
	public String getName() {  return name;  }
	public void setLoc(String loc) { this.loc = loc;}
	public String getLoc() {  return loc;  }
	public void setPass(String pass) { this.pass = pass;}
	public String getPass() {  return pass;  }
	public void setCode(String code) { this.code = code;}
	public String getCode() {  return code;  }	

	public void setUserDiaries(List<String> userDiaries) {
		if (userDiaries == null) {
			this.userDiaries = Collections.emptyList();
		} else {
			this.userDiaries = Collections.unmodifiableList(userDiaries);
		}
	}

	public List<String> getUserDiaries() {
			return userDiaries;
	}
	

	public List<String> getUserDiaries(int start, int end) {
		return userDiaries.subList(start, end);
	}

	public void deleteUserDiary(String userDiary) {
		userDiaries.remove(userDiary);
	}

	public UserDetails toUserDetails() {
		UserDetails userDetails = new UserDetails();

		userDetails.setid(this.id);
		userDetails.setName(this.name);
		userDetails.setDateTimeOfCreation(this.dateTimeOfCreation);
		userDetails.setLoc(this.loc);
		userDetails.setPass(this.pass);
		userDetails.setCode(this.code);  
		userDetails.setUserDiaries(this.userDiaries);
		return userDetails;
	}

	public static User fromUserDetails(UserDetails userDetails) {
		User user = new User();

		user.setid(userDetails.getid());
		user.setCode(userDetails.getCode());
		user.setLoc(userDetails.getLoc());
		user.setName(userDetails.getName());
		user.setPass(userDetails.getPass());
		user.setDateTimeOfCreation(userDetails.getDateTimeOfCreation());
		user.setUserDiaries(userDetails.getUserDiaries());
		
		return user;
	}  
}
