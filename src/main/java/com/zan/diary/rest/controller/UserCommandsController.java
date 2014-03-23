package com.zan.diary.rest.controller;

import com.zan.diary.events.user.CreateUserEvent;
import com.zan.diary.events.user.RequestUserDetailsEventName;
import com.zan.diary.events.user.UpdateUserEvent;
import com.zan.diary.events.user.UserDetailsEvent;
import com.zan.diary.events.user.UserUpdatedEvent;
import com.zan.diary.core.services.UserService;
import com.zan.diary.rest.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Controller
@RequestMapping("/diary/users")
public class UserCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(UserCommandsController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder) {

    	UserDetailsEvent userCreated = userService.createUser(new CreateUserEvent(user.toUserDetails()));

        User newUser = User.fromUserDetails(userCreated.getUserDetails());        

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/diary/users/{id}")
                        .buildAndExpand(userCreated.getid()).toUri());

        return new ResponseEntity<User>(newUser, headers, HttpStatus.CREATED);
    }

   
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        UserUpdatedEvent updateUpdated = userService.updateUser(new UpdateUserEvent(user.toUserDetails()));

        if (!updateUpdated.isEntityFound()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        User updatedUser = User.fromUserDetails(updateUpdated.getUserDetails());

        if (updateUpdated.isUpdateCompleted()) {
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }

        return new ResponseEntity<User>(updatedUser, HttpStatus.FORBIDDEN);
    }
}
