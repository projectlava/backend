package com.zan.diary.persistence.services;


import com.zan.diary.events.user.*;

public interface UserPersistenceService {

	public AllUsersEvent requestUsersNearby(RequestAllUsersEvent requestAllUsersEvent);
	public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent);
	public UserDetailsEvent requestUserDetailsName(RequestUserDetailsEventName requestUserDetailsEvent);
	public UserDetailsEvent createUser(CreateUserEvent createUserEvent);
	public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent);
	public boolean addDiary(String username, String diaryID);
}
