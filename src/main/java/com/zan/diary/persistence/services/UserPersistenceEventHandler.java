package com.zan.diary.persistence.services;

import com.zan.diary.events.user.*;
import com.zan.diary.persistence.domain.PersistenceUser;
import com.zan.diary.persistence.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserPersistenceEventHandler implements UserPersistenceService {

  private UsersRepository usersRepository;

  public UserPersistenceEventHandler(UsersRepository userItemRepository) {
    this.usersRepository = userItemRepository;
  }

  @Override
  public AllUsersEvent requestUsersNearby(RequestAllUsersEvent requestAllUsersEvent) {
    Iterable<PersistenceUser> users = usersRepository.findByloc(requestAllUsersEvent.getloc());

    List<UserDetails> details = new ArrayList<UserDetails>();

    for(PersistenceUser item: users) {
      details.add(item.toUserDetails());
    }

    return new AllUsersEvent(details);
  }

  @Override
  public UserDetailsEvent requestUserDetails(RequestUserDetailsEvent requestUserDetailsEvent) {
	  PersistenceUser item = usersRepository.findByid(requestUserDetailsEvent.getId());

    if (item == null) {
      return UserDetailsEvent.notFound(requestUserDetailsEvent.getId());
    }

    return new UserDetailsEvent(
        requestUserDetailsEvent.getId(),
        item.toUserDetails());
  }

  @Override
  public UserDetailsEvent createUser(CreateUserEvent createUserEvent) {
	  
	  createUserEvent.getDetails().setDateTimeOfCreation(new Date());
	  
	  PersistenceUser item = usersRepository.save(
			  PersistenceUser.fromUserDetails(createUserEvent.getDetails()));

    return new UserDetailsEvent(
        item.getid(),
        item.toUserDetails());
  }

@Override
public UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) {
	PersistenceUser user = usersRepository.findByid(updateUserEvent.getid());

    if(user == null) {
      return UserUpdatedEvent.notFound(updateUserEvent.getid());
    }

    user = usersRepository.save(user);

    return new UserUpdatedEvent(updateUserEvent.getid(), user.toUserDetails());
}

@Override
public UserDetailsEvent requestUserDetailsName(
		RequestUserDetailsEventName requestUserDetailsEvent) {
	PersistenceUser item = usersRepository.findByName(requestUserDetailsEvent.getName());

	if (item == null) {
		return UserDetailsEvent.notFound(-1);
	}

	return new UserDetailsEvent(
			item.getid(),
			item.toUserDetails());
}

@Override
public boolean addDiary(String username, String diaryID) {
	PersistenceUser item = usersRepository.findByName(username);

	if(item == null) { return false;}

	item.addUserDiary(diaryID);	 

	usersRepository.save(item);	 

	return true;
}
}
