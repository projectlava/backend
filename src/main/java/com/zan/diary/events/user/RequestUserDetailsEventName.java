package com.zan.diary.events.user;

import com.zan.diary.events.RequestReadEvent;

public class RequestUserDetailsEventName extends RequestReadEvent {
  private String name;

  public RequestUserDetailsEventName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
