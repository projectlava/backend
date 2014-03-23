package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.UpdatedEvent;

public class UserUpdatedEvent extends UpdatedEvent {

  private UUID id;
  private UserDetails userDetails;
  private boolean updateCompleted;

  public UserUpdatedEvent(UUID id, UserDetails userDetails) {
    this.id = id;
    this.userDetails = userDetails;
    this.updateCompleted = true;
  }

  public UserUpdatedEvent(UUID id) {
    this.id = id;
  }

  public UUID getid() {
    return id;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public static UserUpdatedEvent notFound(UUID uuid) {
    UserUpdatedEvent ev = new UserUpdatedEvent(uuid);
    ev.entityFound=false;
    ev.updateCompleted=false;
    return ev;
  }
  
  public static UserUpdatedEvent updateForbidden(UUID id, UserDetails details) {
	  UserUpdatedEvent ev = new UserUpdatedEvent(id, details);
    ev.entityFound=true;
    ev.updateCompleted=false;
    return ev;
  }
  
  public boolean isUpdateCompleted() {
    return updateCompleted;
  }
}
