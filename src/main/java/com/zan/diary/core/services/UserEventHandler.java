package com.zan.diary.core.services;

import com.zan.diary.events.user.*;
import com.zan.diary.persistence.services.UserPersistenceService;

public class UserEventHandler implements UserService {

	private UserPersistenceService userPersistenceService;

	public UserEventHandler(UserPersistenceService userPersistenceService) {
		this.userPersistenceService = userPersistenceService;
	}

	@Override
	public AllUsersEvent requestAllUsers(RequestAllUsersEvent requestAllUsersEvent) {
		return userPersistenceService.requestUsersNearby(requestAllUsersEvent);
	}

	@Override
	public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent) {
		return userPersistenceService.requestUserDetails(requestUserDetailsEvent);
	}

	@Override
	public UserDetailsEvent createUser(CreateUserEvent createUserEvent) {
		return userPersistenceService.createUser(createUserEvent);
	}

	@Override
	public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) {
		return userPersistenceService.updateUser(updateUserEvent);
	}

	@Override
	public UserDetailsEvent requestUserDetailsName(
			RequestUserDetailsEventName requestUserDetailsEvent) {
		return userPersistenceService.requestUserDetailsName(requestUserDetailsEvent);
	}

	@Override
	public boolean addDiary(String username, String diaryID) {
		return userPersistenceService.addDiary(username, diaryID);
	}
}
