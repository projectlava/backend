package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.RequestReadEvent;

public class RequestUserDetailsEvent extends RequestReadEvent {
  private UUID id;

  public RequestUserDetailsEvent(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
