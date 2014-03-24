package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.CreatedEvent;


public class DiaryCreatedEvent extends CreatedEvent {

  private final UUID newDiaryid;
  private final DiaryDetails details;

  public DiaryCreatedEvent(final UUID uuid, final DiaryDetails details) {
    this.newDiaryid = uuid;
    this.details = details;
  }

  public DiaryDetails getDiaryDetails() {
    return details;
  }

  public UUID getNewDiaryid() {
    return newDiaryid;
  }
}
