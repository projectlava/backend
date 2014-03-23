package com.zan.diary.events.user;


import com.zan.diary.events.ReadEvent;

import java.util.List;

public class AllUsersEvent extends ReadEvent {
  private List<UserDetails> UserDetails;

  public AllUsersEvent(List<UserDetails> UserDetails) {
    this.UserDetails = UserDetails;
  }

  public List<UserDetails> getUserDetails() {
    return UserDetails;
  }
}
