package com.zan.diary.core.services;

import com.zan.diary.core.domain.Diary;
import com.zan.diary.events.diary.*;
import com.zan.diary.persistence.services.DiaryPersistenceService;


public class DiaryEventHandler implements DiaryService {

  private final DiaryPersistenceService DiaryPersistenceService;

  public DiaryEventHandler(final DiaryPersistenceService diariesPersistenceService) {
    this.DiaryPersistenceService = diariesPersistenceService;
  }

  @Override
  public DiaryCreatedEvent createDiary(CreateDiaryEvent createDiaryEvent) {

    DiaryCreatedEvent event = DiaryPersistenceService.createDiary(createDiaryEvent);

    // generate http response    
    
    return event;
  }

  @Override
  public DiaryDetailsEvent requestDiaryDetails(RequestDiaryDetailsEvent requestDiaryDetailsEvent) {
    return DiaryPersistenceService.requestDiaryDetails(requestDiaryDetailsEvent);
  }

  @Override
  public DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent) {

    DiaryDetailsEvent diaryDetailsEvent = DiaryPersistenceService.requestDiaryDetails(new RequestDiaryDetailsEvent(deleteDiaryEvent.getid()));

    if (!diaryDetailsEvent.isEntityFound()) {
      return DiaryDeletedEvent.notFound(deleteDiaryEvent.getid());
    }

    Diary diary = Diary.fromDiaryDetails(diaryDetailsEvent.getDiaryDetails());

    DiaryPersistenceService.deleteDiary(deleteDiaryEvent);

    return new DiaryDeletedEvent(deleteDiaryEvent.getid(), diary.toDiaryDetails());
  }

@Override
public DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent) {
	
    DiaryDetailsEvent diaryDetailsEvent = DiaryPersistenceService.requestDiaryDetails(new RequestDiaryDetailsEvent(updateDiaryEvent.getid()));

    if (!diaryDetailsEvent.isEntityFound()) {
      return DiaryUpdatedEvent.notFound(updateDiaryEvent.getid());
    }
    
    Diary diary = Diary.fromDiaryDetails(diaryDetailsEvent.getDiaryDetails());

    DiaryPersistenceService.updateDiary(updateDiaryEvent);

    return new DiaryUpdatedEvent(updateDiaryEvent.getid(), diary.toDiaryDetails());
}

}
