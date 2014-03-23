package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.DeletedEvent;


public class DiaryDeletedEvent extends DeletedEvent {

  private UUID id;
  private DiaryDetails details;
  private boolean deletionCompleted;

  private DiaryDeletedEvent(UUID id) {
	this.id = id;
  }

  public DiaryDeletedEvent(UUID id, DiaryDetails details) {
	this.id = id;
    this.details = details;
    this.deletionCompleted = true;
  }

  public UUID getid() {
    return id;
  }

  public DiaryDetails getDiaryDetails() {
    return details;
  }

  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }

  public static DiaryDeletedEvent deletionForbidden(UUID id, DiaryDetails details) {
    DiaryDeletedEvent ev = new DiaryDeletedEvent(id, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }

  public static DiaryDeletedEvent notFound(UUID id) {
    DiaryDeletedEvent ev = new DiaryDeletedEvent(id);
    ev.entityFound=false;
    return ev;
  }
}
