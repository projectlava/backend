package com.zan.diary.rest.controller;

import com.zan.diary.events.user.*;
import com.zan.diary.core.services.UserService;
import com.zan.diary.rest.domain.User;
import com.zan.diary.rest.domain.UserQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/diary/getusergroups")
public class UserGroupQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(UserGroupQueriesController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers(@RequestBody UserQuery userQuery, UriComponentsBuilder builder){
        List<User> users = new ArrayList<User>();
        
        AllUsersEvent allUsersEvent = userService.requestAllUsers(new RequestAllUsersEvent(userQuery.getLoc()));
        for (UserDetails detail : allUsersEvent.getUserDetails()) {
        	users.add(User.fromUserDetails(detail));
        }
        return users;
    }
}
