package com.zan.diary.persistence.services;

import com.zan.diary.events.diary.*;

public interface DiaryPersistenceService {

  public DiaryDetailsEvent requestDiaryDetails(RequestDiaryDetailsEvent requestDiaryDetailsEvent);

  public DiaryCreatedEvent createDiary(CreateDiaryEvent event);

  public DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent);

  public DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent);

}
