package com.zan.diary.persistence.services;

import java.util.UUID;

import com.zan.diary.events.diary.*;
import com.zan.diary.persistence.domain.PersistenceDiary;
import com.zan.diary.persistence.repository.DiaryRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

public class DiaryPersistenceEventHandler implements DiaryPersistenceService {

  private final DiaryRepository diaryRepository;
  
  @Autowired
  private UserPersistenceService userService;

  public DiaryPersistenceEventHandler(
      final DiaryRepository diaryRepository) {
    this.diaryRepository = diaryRepository;
  }

  @Override
  public DiaryCreatedEvent createDiary(CreateDiaryEvent createDiaryEvent) {
	  PersistenceDiary diary = PersistenceDiary.fromDiaryDetails(createDiaryEvent.getDetails());

	  diary.setid(UUID.randomUUID());
	  diary.setDateTimeOfCreation(new Date());
	  diary.setDateTimeOfModification(new Date());
	  userService.addDiary(diary.getOwner(), diary.getid().toString());

	  diary = diaryRepository.save(diary);

	  return new DiaryCreatedEvent(diary.getid(), diary.toDiaryDetails());
  }
  @Override
  public DiaryDetailsEvent requestDiaryDetails(RequestDiaryDetailsEvent requestDiaryDetailsEvent) {

    PersistenceDiary diary = diaryRepository.findByid(requestDiaryDetailsEvent.getid());

    if (diary == null) {
      return DiaryDetailsEvent.notFound(requestDiaryDetailsEvent.getid());
    }

    return new DiaryDetailsEvent(
        requestDiaryDetailsEvent.getid(),
        diary.toDiaryDetails());
  }

  @Override
  public DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent) {
    PersistenceDiary diary = diaryRepository.findByid(updateDiaryEvent.getid());

    if(diary == null) {
      return DiaryUpdatedEvent.notFound(updateDiaryEvent.getid());
    }

    diary = PersistenceDiary.fromDiaryDetails(updateDiaryEvent.getDetails());
    diary.setDateTimeOfModification(new Date());
    diary = diaryRepository.save(diary);

    return new DiaryUpdatedEvent(updateDiaryEvent.getid(), diary.toDiaryDetails());
  }

  @Override
  public DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent) {

    PersistenceDiary diary = diaryRepository.findByid(deleteDiaryEvent.getid());

    if (diary == null) {
      return DiaryDeletedEvent.notFound(deleteDiaryEvent.getid());
    }

    diaryRepository.delete(deleteDiaryEvent.getid().toString());

    return new DiaryDeletedEvent(deleteDiaryEvent.getid(), diary.toDiaryDetails());
  }

}
