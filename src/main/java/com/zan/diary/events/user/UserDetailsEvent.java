package com.zan.diary.events.user;


import java.util.UUID;

import com.zan.diary.events.ReadEvent;

public class UserDetailsEvent extends ReadEvent {
  private UUID id;
  private UserDetails userDetails;

  private UserDetailsEvent() {}

  public UserDetailsEvent(UUID id, UserDetails userDetails) {
	this.id = id;
    this.userDetails = userDetails;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public UUID getid() {
    return id;
  }

  public static UserDetailsEvent notFound(UUID id) {
    UserDetailsEvent ev = new UserDetailsEvent();
    ev.id = id;
    ev.entityFound=false;
    return ev;
  }
}
