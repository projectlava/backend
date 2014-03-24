package com.zan.diary.events.diary;

import com.zan.diary.events.CreateEvent;

public class CreateDiaryEvent extends CreateEvent {
  private DiaryDetails details;

  public CreateDiaryEvent(DiaryDetails details) {
    this.details = details;
  }

  public DiaryDetails getDetails() {
    return details;
  }
}
