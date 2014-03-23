package com.zan.diary.core.services;

import com.zan.diary.events.user.*;

public interface UserService {
  AllUsersEvent requestAllUsers(RequestAllUsersEvent requestAllUsersEvent);
  UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent);
  UserDetailsEvent requestUserDetailsName(RequestUserDetailsEventName requestUserDetailsEvent);
  UserDetailsEvent createUser(CreateUserEvent createUserEvent);
  UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent);
  boolean addDiary(String username, String diaryID);
}
