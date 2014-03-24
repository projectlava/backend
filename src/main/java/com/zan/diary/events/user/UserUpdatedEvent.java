package com.zan.diary.events.user;

import java.util.UUID;

import com.zan.diary.events.UpdatedEvent;

public class UserUpdatedEvent extends UpdatedEvent {

  private long id;
  private UserDetails userDetails;
  private boolean updateCompleted;

  public UserUpdatedEvent(long id, UserDetails userDetails) {
    this.id = id;
    this.userDetails = userDetails;
    this.updateCompleted = true;
  }

  public UserUpdatedEvent(long id) {
    this.id = id;
  }

  public long getid() {
    return id;
  }

  public UserDetails getUserDetails() {
    return userDetails;
  }

  public static UserUpdatedEvent notFound(long id) {
    UserUpdatedEvent ev = new UserUpdatedEvent(id);
    ev.entityFound=false;
    ev.updateCompleted=false;
    return ev;
  }
  
  public static UserUpdatedEvent updateForbidden(long id, UserDetails details) {
	  UserUpdatedEvent ev = new UserUpdatedEvent(id, details);
    ev.entityFound=true;
    ev.updateCompleted=false;
    return ev;
  }
  
  public boolean isUpdateCompleted() {
    return updateCompleted;
  }
}
