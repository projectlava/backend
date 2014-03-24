package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.ReadEvent;


public class DiaryDetailsEvent extends ReadEvent {
  private UUID id;
  private DiaryDetails diaryDetails;

  private DiaryDetailsEvent(UUID id) {
    this.id = id;
  }

  public DiaryDetailsEvent(UUID id, DiaryDetails diaryDetails) {
    this.id = id;
    this.diaryDetails = diaryDetails;
  }

  public UUID getid() {
    return id;
  }

  public DiaryDetails getDiaryDetails() {
    return diaryDetails;
  }

  public static DiaryDetailsEvent notFound(UUID id) {
    DiaryDetailsEvent ev = new DiaryDetailsEvent(id);
    ev.entityFound=false;
    return ev;
  }
}
