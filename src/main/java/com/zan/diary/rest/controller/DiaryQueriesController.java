package com.zan.diary.rest.controller;

import java.util.UUID;

import com.zan.diary.events.diary.DeleteDiaryEvent;
import com.zan.diary.events.diary.DiaryDeletedEvent;
import com.zan.diary.events.diary.DiaryDetailsEvent;
import com.zan.diary.events.diary.DiaryUpdatedEvent;
import com.zan.diary.events.diary.RequestDiaryDetailsEvent;
import com.zan.diary.events.diary.UpdateDiaryEvent;
import com.zan.diary.events.user.RequestUserDetailsEventName;
import com.zan.diary.events.user.UserDetailsEvent;
import com.zan.diary.core.services.DiaryService;
import com.zan.diary.rest.domain.RestDiary;
import com.zan.diary.rest.domain.DiaryQuery;
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

@Controller
@RequestMapping("/diary/getdiaries")
public class DiaryQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(DiaryQueriesController.class);

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestDiary> viewDiary(@RequestBody DiaryQuery diaryQuery, UriComponentsBuilder builder) {

    	RestDiary diary = null;

    	if(diaryQuery.getOperon().equals("0")) {   //read
    		DiaryDetailsEvent details = diaryService.requestDiaryDetails(new RequestDiaryDetailsEvent(UUID.fromString(diaryQuery.getDiaryid())));

    		if (!details.isEntityFound()) {
    			return new ResponseEntity<RestDiary>(HttpStatus.NOT_FOUND);
    		}
    		
    		diary = RestDiary.fromDiaryDetails(details.getDiaryDetails());
    	}
    	else if(diaryQuery.getOperon().equals("1")) {  //modify
    		DiaryUpdatedEvent details = diaryService.updateDiary(new UpdateDiaryEvent(diaryQuery.getDiaryDetails()));
    		
    		if (!details.isEntityFound()) {
    			return new ResponseEntity<RestDiary>(HttpStatus.NOT_FOUND);
    		}
    		if (!details.isUpdateCompleted()) {
    			return new ResponseEntity<RestDiary>(HttpStatus.NOT_MODIFIED);
    		}
    		
    		diary = RestDiary.fromDiaryDetails(details.getDiaryDetails());    		
    	}    	
    	else if(diaryQuery.getOperon().equals("2")) {  //delete
    		DiaryDeletedEvent details = diaryService.deleteDiary(new DeleteDiaryEvent(UUID.fromString(diaryQuery.getDiaryid())));
    		
    		if (!details.isEntityFound()) {
    			return new ResponseEntity<RestDiary>(HttpStatus.NOT_FOUND);
    		}
    		if (!details.isDeletionCompleted()) {
    			return new ResponseEntity<RestDiary>(HttpStatus.NOT_ACCEPTABLE);
    		}
    		
    		diary = RestDiary.fromDiaryDetails(details.getDiaryDetails());    		
    	}    	
    	
        return new ResponseEntity<RestDiary>(diary, HttpStatus.OK);
    }  
}
