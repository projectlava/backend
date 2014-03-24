package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.UpdateEvent;

public class UpdateUserEvent extends UpdateEvent {
  private UserDetails details;
  private long id;
  
  public UpdateUserEvent(UserDetails details) {
    this.details = details;
  }

  public long getid () { return id;};
  
  public UserDetails getDetails() {
    return details;
  }
}
