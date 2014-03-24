package com.zan.diary.events.user;


import com.zan.diary.events.CreateEvent;

public class CreateUserEvent extends CreateEvent {

  private UserDetails details;

  public CreateUserEvent(UserDetails details) {
    this.details = details;
  }

  public UserDetails getDetails() {
    return details;
  }
}
