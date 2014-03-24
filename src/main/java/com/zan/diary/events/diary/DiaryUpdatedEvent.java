package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.UpdatedEvent;

public class DiaryUpdatedEvent extends UpdatedEvent {

  private UUID id;
  private DiaryDetails diaryDetails;

  private boolean updateCompleted;

  public DiaryUpdatedEvent(UUID id, DiaryDetails diaryDetails) {
    this.id = id;
    this.diaryDetails = diaryDetails;
    this.updateCompleted = true;
  }

  public DiaryUpdatedEvent(UUID id) {
    this.id = id;
  }

  public UUID getid() {
    return id;
  }

  public DiaryDetails getDiaryDetails() {
    return diaryDetails;
  }

  public static DiaryUpdatedEvent notFound(UUID uuid) {
    DiaryUpdatedEvent ev = new DiaryUpdatedEvent(uuid);
    ev.entityFound=false;
    ev.updateCompleted=false;
    return ev;
  }
  
  public static DiaryUpdatedEvent updateForbidden(UUID id, DiaryDetails details) {
	  DiaryUpdatedEvent ev = new DiaryUpdatedEvent(id, details);
    ev.entityFound=true;
    ev.updateCompleted=false;
    return ev;
  }

  public boolean isUpdateCompleted() {
	return updateCompleted;
  }
}
