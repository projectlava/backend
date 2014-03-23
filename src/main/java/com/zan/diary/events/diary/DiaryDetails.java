package com.zan.diary.events.diary;

import java.util.Date;
import java.util.UUID;

public class DiaryDetails {

	private UUID id;
	private String title;
	private String text;
	private String subject;
	private String owner;
	private Date dateTimeOfCreation;
	private Date dateTimeOfModification;
	private boolean publicDiary;

	public void setDateTimeOfCreation(Date dateTimeOfCreation) {   this.dateTimeOfCreation = dateTimeOfCreation;  }
	public Date getDateTimeOfCreation() {    return dateTimeOfCreation;  }

	public void setDateTimeOfModification(Date dateTimeOfModification) {   this.dateTimeOfModification = dateTimeOfModification;  }
	public Date getDateTimeOfModification() {    return dateTimeOfModification;  }

	public String getText() {	return text;}
	public void setText(String Text) { this.text = Text;}

	public String getSubject() { return subject;}	
	public void setSubject(String subject) { this.subject = subject; }

	public UUID getid() {  return id; }
	public void setid(UUID id) { this.id = id; }

	public String getTitle() {  return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	} 

	public boolean isPublicDiary() {
		return publicDiary;
	}
	public void setPublicDiary(boolean publicDiary) {
		this.publicDiary = publicDiary;
	}
}
