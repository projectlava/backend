package com.zan.diary.core.repository;

import com.zan.diary.events.user.AllUsersEvent;
import com.zan.diary.events.user.CreateUserEvent;
import com.zan.diary.events.user.RequestAllUsersEvent;
import com.zan.diary.events.user.RequestUserDetailsEvent;
import com.zan.diary.events.user.RequestUserDetailsEventName;
import com.zan.diary.events.user.UpdateUserEvent;
import com.zan.diary.events.user.UserDetails;
import com.zan.diary.events.user.UserDetailsEvent;
import com.zan.diary.events.user.UserUpdatedEvent;
import com.zan.diary.core.domain.User;

import java.util.*;

public class UsersMemoryRepository implements UsersRepository {

	private Map<UUID, User> users;

	public UsersMemoryRepository(final Map<UUID, User> users) {
		this.users = Collections.unmodifiableMap(users);
	}


	public synchronized AllUsersEvent requestUsersNearby(
			RequestAllUsersEvent requestAllUsersEvent) {

		ArrayList<User> userList = new ArrayList<User>(users.values());
		ArrayList<User> usersLoc =  new ArrayList<User>();


		for(User usr:userList) {
			if(usr.getLoc().equals(requestAllUsersEvent.getloc()))
				usersLoc.add(usr);
		}	

		List<UserDetails> details = new ArrayList<UserDetails>();

		for(User item: usersLoc) {
			details.add(item.toUserDetails());
		}

		return new AllUsersEvent(details);
	}

	public synchronized UserDetailsEvent requestUserDetails(
			RequestUserDetailsEvent requestUserDetailsEvent) {
		User item = users.get(requestUserDetailsEvent.getId());

		if (item == null) {
			return UserDetailsEvent.notFound(requestUserDetailsEvent.getId());
		}

		return new UserDetailsEvent(
				requestUserDetailsEvent.getId(),
				item.toUserDetails());
	}
	
	public synchronized UserDetailsEvent createUser(CreateUserEvent createUserEvent) {
		UserDetails userDetails= createUserEvent.getDetails();

		userDetails.setDateTimeOfCreation(new Date());
		userDetails.setid(UUID.randomUUID());		
		
		Map<UUID, User> modifiableUsers = new HashMap<UUID, User>(users);
		modifiableUsers.put(createUserEvent.getDetails().getid(), User.fromUserDetails(createUserEvent.getDetails()));
		this.users = Collections.unmodifiableMap(modifiableUsers);

		return new UserDetailsEvent(
				createUserEvent.getDetails().getid(),
				createUserEvent.getDetails());    
	}
	
	// internal use, called by diary handler
	
	public synchronized boolean addDiary(String username, String diaryID) {

		User item = null;

		List<User> list = new ArrayList<User>(users.values());
				
		for(User usr:list) {
			if(usr.getName().equals(username)) {
				item = usr;
				break;
			}
		}					
		
		if(item==null)
			return false;

		item.addUserDiary(diaryID);
		
		Map<UUID, User> modifiableUsers = new HashMap<UUID, User>(users);
		modifiableUsers.put(item.getid(), item);
		this.users = Collections.unmodifiableMap(modifiableUsers);
		
		return true;
	}

	public synchronized UserUpdatedEvent updateUser(UpdateUserEvent updateUserEvent) {
		User user = users.get(updateUserEvent.getid());

	    if(user == null) {
	      return UserUpdatedEvent.notFound(updateUserEvent.getid());
	    }

		Map<UUID, User> modifiableUsers = new HashMap<UUID, User>(users);
		modifiableUsers.put(updateUserEvent.getid(), user);
		this.users = Collections.unmodifiableMap(modifiableUsers);

	    return new UserUpdatedEvent(updateUserEvent.getid(), user.toUserDetails());	    
	}


	@Override
	public UserDetailsEvent requestUserDetailsName(
			RequestUserDetailsEventName requestUserDetailsEvent) {
		
		String name = requestUserDetailsEvent.getName();
		User item = null;

		List<User> list = new ArrayList<User>(users.values());
				
		for(User usr:list) {
			if(usr.getName().equals(name)) {
				item = usr;
				break;
			}
		}					

		if (item == null) {
			return UserDetailsEvent.notFound(UUID.randomUUID());
		}

		return new UserDetailsEvent(
				item.getid(),
				item.toUserDetails());
	}

}
