package com.zan.diary.persistence.services;

import com.zan.diary.events.user.*;
import com.zan.diary.persistence.domain.User;
import com.zan.diary.persistence.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserPersistenceEventHandler implements UserPersistenceService {

  private UsersRepository usersRepository;

  public UserPersistenceEventHandler(UsersRepository userItemRepository) {
    this.usersRepository = userItemRepository;
  }

  @Override
  public AllUsersEvent requestUsersNearby(RequestAllUsersEvent requestAllUsersEvent) {
    Iterable<User> users = usersRepository.findByloc(requestAllUsersEvent.getloc());

    List<UserDetails> details = new ArrayList<UserDetails>();

    for(User item: users) {
      details.add(item.toUserDetails());
    }

    return new AllUsersEvent(details);
  }

  @Override
  public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent) {
	  User item = usersRepository.findByid(requestUserDetailsEvent.getId());

    if (item == null) {
      return UserDetailsEvent.notFound(requestUserDetailsEvent.getId());
    }

    return new UserDetailsEvent(
        requestUserDetailsEvent.getId(),
        item.toUserDetails());
  }

  @Override
  public UserDetailsEvent createUser(CreateUserEvent createUserEvent) {
	  User item = usersRepository.save(
			  User.fromUserDetails(createUserEvent.getDetails()));

    return new UserDetailsEvent(
        item.getid(),
        item.toUserDetails());
  }

@Override
public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) {
	User user = usersRepository.findByid(updateUserEvent.getid());

    if(user == null) {
      return UserUpdatedEvent.notFound(updateUserEvent.getid());
    }

    user = usersRepository.save(user);

    return new UserUpdatedEvent(updateUserEvent.getid(), user.toUserDetails());
}

@Override
public UserDetailsEvent requestUserDetailsName(
		RequestUserDetailsEventName requestUserDetailsEvent) {
	User item = usersRepository.findByName(requestUserDetailsEvent.getName());

	if (item == null) {
		return UserDetailsEvent.notFound(UUID.randomUUID());
	}

	return new UserDetailsEvent(
			item.getid(),
			item.toUserDetails());
}

@Override
public boolean addDiary(String username, String diaryID) {
	User item = usersRepository.findByName(username);

	if(item == null) { return false;}

	item.addUserDiary(diaryID);	 

	usersRepository.save(item);	 

	return true;
}
}
