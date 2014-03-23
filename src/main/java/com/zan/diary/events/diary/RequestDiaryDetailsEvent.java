package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.RequestReadEvent;

public class RequestDiaryDetailsEvent extends RequestReadEvent {
  private UUID id;

  public RequestDiaryDetailsEvent(UUID id) {
    this.id = id;
  }

  public UUID getid() {
    return id;
  }
}
