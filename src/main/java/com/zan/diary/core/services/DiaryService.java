package com.zan.diary.core.services;

import com.zan.diary.events.diary.*;

//TODOCUMENT THis is an event driven service.
// Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface DiaryService {

  public DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent);

  public DiaryDetailsEvent requestDiaryDetails(RequestDiaryDetailsEvent requestDiaryDetailsEvent);

  public DiaryCreatedEvent createDiary(CreateDiaryEvent event);

  public DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent);
}
