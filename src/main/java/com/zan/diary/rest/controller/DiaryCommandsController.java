package com.zan.diary.rest.controller;

import com.zan.diary.events.diary.CreateDiaryEvent;
import com.zan.diary.events.diary.DiaryCreatedEvent;
import com.zan.diary.events.diary.DiaryUpdatedEvent;
import com.zan.diary.events.diary.UpdateDiaryEvent;
import com.zan.diary.events.user.CreateUserEvent;
import com.zan.diary.events.user.UpdateUserEvent;
import com.zan.diary.events.user.UserDetailsEvent;
import com.zan.diary.events.user.UserUpdatedEvent;
import com.zan.diary.core.services.DiaryService;
import com.zan.diary.core.services.UserService;
import com.zan.diary.rest.domain.RestDiary;
import com.zan.diary.rest.domain.RestUser;

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
@RequestMapping("/diary/diaries")
public class DiaryCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(DiaryCommandsController.class);

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestDiary> createUser(@RequestBody RestDiary diary, UriComponentsBuilder builder) {

    	DiaryCreatedEvent diaryCreated = diaryService.createDiary(new CreateDiaryEvent(diary.toDiaryDetails()));

    	RestDiary newDiary = RestDiary.fromDiaryDetails(diaryCreated.getDiaryDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/diary/diaries/{id}")
                        .buildAndExpand(diaryCreated.getNewDiaryid()).toUri());

        return new ResponseEntity<RestDiary>(newDiary, headers, HttpStatus.CREATED);
    }
}
