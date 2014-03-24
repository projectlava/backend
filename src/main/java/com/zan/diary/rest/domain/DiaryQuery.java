package com.zan.diary.rest.domain;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.zan.diary.events.diary.DiaryDetails;

public class DiaryQuery extends ResourceSupport implements Serializable {

	private String name;
	private String pass;
	private String diaryid;
	private boolean publicDiary;
	private String operon; // 0, read, 1, modify, 2 delete, 3 make public, 4 make private
	private DiaryDetails diaryDetails;
	
	public void setName(String name) { this.name = name;}
	public String getName() {  return name;  }
	public void setPass(String pass) { this.pass = pass;}
	public String getPass() {  return pass;  }
	public boolean isPublicDiary() {return publicDiary;	}
	public void setPublicDiary(boolean publicDiary) {this.publicDiary = publicDiary;}
	public String getOperon() {
		return operon;
	}
	public void setOperon(String operon) {
		this.operon = operon;
	}
	public String getDiaryid() {
		return diaryid;
	}
	public void setDiaryid(String diaryid) {
		this.diaryid = diaryid;
	}
	public DiaryDetails getDiaryDetails() {
		return diaryDetails;
	}
	public void setDiaryDetails(DiaryDetails diaryDetails) {
		this.diaryDetails = diaryDetails;
	}

}
