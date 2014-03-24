package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.RequestReadEvent;

public class RequestUserDetailsEvent extends RequestReadEvent {
  private long id;

  public RequestUserDetailsEvent(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
