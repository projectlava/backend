package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.UpdateEvent;

public class UpdateUserEvent extends UpdateEvent {
  private UserDetails details;
  private UUID id;
  
  public UpdateUserEvent(UserDetails details) {
    this.details = details;
  }

  public UUID getid () { return id;};
  
  public UserDetails getDetails() {
    return details;
  }
}
