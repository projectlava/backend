package com.zan.diary.persistence.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zan.diary.events.diary.DiaryDetails;

import java.util.Date;
import java.util.UUID;

// {!begin top}
@Document(collection = "diary")
public class PersistenceDiary {
  
	@Id
	private UUID id;
	@Field("diaryTitle")
	@Indexed
	private String title;
	private String text;
	private String subject;
	private Date dateTimeOfCreation;
	private Date dateTimeOfModification;
	private String owner;
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

	public DiaryDetails toDiaryDetails() {
		DiaryDetails details = new DiaryDetails();

		details.setDateTimeOfCreation(dateTimeOfCreation);
		details.setDateTimeOfModification(dateTimeOfModification);
		details.setid(id);
		details.setSubject(subject);
		details.setText(text);
		details.setTitle(title);
		details.setOwner(owner);
		details.setPublicDiary(publicDiary);
		return details;
	}

	public static PersistenceDiary fromDiaryDetails(DiaryDetails diaryDetails) {

		PersistenceDiary diary = new PersistenceDiary();

		diary.setDateTimeOfCreation(diaryDetails.getDateTimeOfCreation());
		diary.setDateTimeOfModification(diaryDetails.getDateTimeOfModification());
		diary.setid(diaryDetails.getid());
		diary.setSubject(diaryDetails.getSubject());
		diary.setText(diaryDetails.getText());
		diary.setTitle(diaryDetails.getTitle());
		diary.setOwner(diaryDetails.getOwner());
		diary.setPublicDiary(diaryDetails.isPublicDiary());
		return diary;
	}
	public boolean isPublicDiary() {
		return publicDiary;
	}
	public void setPublicDiary(boolean publicDiary) {
		this.publicDiary = publicDiary;
	}
  
}