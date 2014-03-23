package com.zan.diary.core.repository;

import com.zan.diary.events.user.AllUsersEvent;
import com.zan.diary.events.user.CreateUserEvent;
import com.zan.diary.events.user.RequestAllUsersEvent;
import com.zan.diary.events.user.RequestUserDetailsEvent;
import com.zan.diary.events.user.RequestUserDetailsEventName;
import com.zan.diary.events.user.UpdateUserEvent;
import com.zan.diary.events.user.UserDetailsEvent;
import com.zan.diary.events.user.UserUpdatedEvent;

//TODO, make this event based again, with persistence integration events.
public interface UsersRepository {

	AllUsersEvent requestUsersNearby(RequestAllUsersEvent requestAllUsersEvent);
	UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent);
	UserDetailsEvent requestUserDetailsName(RequestUserDetailsEventName requestUserDetailsEvent);
	UserDetailsEvent createUser(CreateUserEvent createUserEvent);
	UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent);
	boolean addDiary(String username, String diaryID);
}
