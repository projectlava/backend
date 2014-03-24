package com.zan.diary.rest.controller;

import com.zan.diary.events.user.*;
import com.zan.diary.core.services.UserService;
import com.zan.diary.rest.domain.RestUser;
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
@RequestMapping("/diary/getusers")
public class UserSingleQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(UserSingleQueriesController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestUser> getUser(@RequestBody UserQuery userQuery, UriComponentsBuilder builder) {   	
    	
    	UserDetailsEvent details = userService.requestUserDetailsName(new RequestUserDetailsEventName(userQuery.getName()));

    	// security check
    	boolean safe = false;
    	
    	if(userQuery.getCode()!=null) {
    		if(userQuery.getCode().equals(details.getUserDetails().getCode()))
    			safe = true;
    	} 
    	else if(userQuery.getPass()!=null) {
    		if(userQuery.getPass().equals(details.getUserDetails().getPass()))
    			safe = true;
    	} 
    	
    	if(!safe) {
    		return new ResponseEntity<RestUser>(HttpStatus.FORBIDDEN);
    	}
    	
        if (!details.isEntityFound()) {
            return new ResponseEntity<RestUser>(HttpStatus.NOT_FOUND);
        }

        RestUser user = RestUser.fromUserDetails(details.getUserDetails());  

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/diary/users/{id}")
                        .buildAndExpand(details.getid()).toUri());

        return new ResponseEntity<RestUser>(user, headers, HttpStatus.OK);
    }
}
