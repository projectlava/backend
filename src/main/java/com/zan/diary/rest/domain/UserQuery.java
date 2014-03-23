package com.zan.diary.rest.domain;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class UserQuery extends ResourceSupport implements Serializable {

	private String name;
	private String loc;
	private String pass;
	private String code;

	
	public void setName(String name) { this.name = name;}
	public String getName() {  return name;  }
	public void setLoc(String loc) { this.loc = loc;}
	public String getLoc() {  return loc;  }
	public void setPass(String pass) { this.pass = pass;}
	public String getPass() {  return pass;  }
	public void setCode(String code) { this.code = code;}
	public String getCode() {  return code;  }		
}
