package com.zan.diary.events.user;

import com.zan.diary.events.RequestReadEvent;

public class RequestAllUsersEvent extends RequestReadEvent {
	
	  private final String loc;

	  public RequestAllUsersEvent(final String loc) {
	    this.loc = loc;
	  }

	  public String getloc() {
	    return loc;
	  }

}
