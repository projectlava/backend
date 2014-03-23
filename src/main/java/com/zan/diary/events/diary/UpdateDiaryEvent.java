package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.UpdateEvent;

public class UpdateDiaryEvent extends UpdateEvent {
  private DiaryDetails details;
  private UUID id;
  
  public UpdateDiaryEvent(DiaryDetails details) {
    this.details = details;
  }

  public UUID getid () { return id;};
  
  public DiaryDetails getDetails() {
    return details;
  }
}
