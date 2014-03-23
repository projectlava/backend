package com.zan.diary.core.repository;

import com.zan.diary.events.diary.CreateDiaryEvent;
import com.zan.diary.events.diary.DeleteDiaryEvent;
import com.zan.diary.events.diary.DiaryCreatedEvent;
import com.zan.diary.events.diary.DiaryDeletedEvent;
import com.zan.diary.events.diary.DiaryDetailsEvent;
import com.zan.diary.events.diary.DiaryUpdatedEvent;
import com.zan.diary.events.diary.RequestDiaryDetailsEvent;
import com.zan.diary.events.diary.UpdateDiaryEvent;
import com.zan.diary.core.domain.Diary;
import com.zan.diary.core.services.UserService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

public class DiaryMemoryRepository implements DiaryRepository {

	private Map<UUID, Diary> diaries;
	
	  @Autowired
	  private UserService userService;

	public DiaryMemoryRepository(final Map<UUID, Diary> diaries) {
		this.diaries = Collections.unmodifiableMap(diaries);
	}

	@Override
	public synchronized DiaryDetailsEvent requestDiaryDetails(
		RequestDiaryDetailsEvent requestDiaryDetailsEvent) {
		Diary item = diaries.get(requestDiaryDetailsEvent.getid());

		if (item == null) {
			return DiaryDetailsEvent.notFound(requestDiaryDetailsEvent.getid());
		}

		return new DiaryDetailsEvent(
				requestDiaryDetailsEvent.getid(),
				item.toDiaryDetails());
	}


	@Override
	public synchronized DiaryCreatedEvent createDiary(CreateDiaryEvent event) {
		Map<UUID, Diary> modifiableDiaries = new HashMap<UUID, Diary>(diaries);
		Diary diary = Diary.fromDiaryDetails(event.getDetails());
		
	    diary.setid(UUID.randomUUID());
	    diary.setDateTimeOfCreation(new Date());
	    diary.setDateTimeOfModification(new Date());	    

	    userService.addDiary(diary.getOwner(), diary.getid().toString());
	    
		modifiableDiaries.put(diary.getid(), diary);
		this.diaries = Collections.unmodifiableMap(modifiableDiaries);
	
	    return new DiaryCreatedEvent(diary.getid(), diary.toDiaryDetails());  
	}


	@Override
	public synchronized DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent) {
		
		Diary diary = diaries.get(updateDiaryEvent.getid());

	    if(diary == null) {
	      return DiaryUpdatedEvent.notFound(updateDiaryEvent.getid());
	    }
	    
	    diary.setDateTimeOfModification(new Date());

	    Map<UUID, Diary> modifiableDiaries = new HashMap<UUID, Diary>(diaries);
		modifiableDiaries.put(updateDiaryEvent.getDetails().getid(), diary);
		this.diaries = Collections.unmodifiableMap(modifiableDiaries);

	    return new DiaryUpdatedEvent(diary.getid(), diary.toDiaryDetails());
	}


	@Override
	public synchronized DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent) {
		Diary diary = diaries.get(deleteDiaryEvent.getid());

	    if(diary == null) {
	      return DiaryDeletedEvent.notFound(deleteDiaryEvent.getid());
	    }

	    Map<UUID, Diary> modifiableDiaries = new HashMap<UUID, Diary>(diaries);
		modifiableDiaries.remove(deleteDiaryEvent.getid());
		this.diaries = Collections.unmodifiableMap(modifiableDiaries);
		
		return new DiaryDeletedEvent(deleteDiaryEvent.getid(), diary.toDiaryDetails());
	}

}
