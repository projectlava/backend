package com.zan.diary.events.diary;

import java.util.UUID;

import com.zan.diary.events.DeleteEvent;


public class DeleteDiaryEvent extends DeleteEvent {

  private final UUID id;

  public DeleteDiaryEvent(final UUID id) {
    this.id = id;
  }

  public UUID getid() {
    return id;
  }
}
