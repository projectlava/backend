package com.zan.diary.core.repository;

import com.zan.diary.events.diary.CreateDiaryEvent;
import com.zan.diary.events.diary.DeleteDiaryEvent;
import com.zan.diary.events.diary.DiaryCreatedEvent;
import com.zan.diary.events.diary.DiaryDeletedEvent;
import com.zan.diary.events.diary.DiaryDetailsEvent;
import com.zan.diary.events.diary.DiaryUpdatedEvent;
import com.zan.diary.events.diary.RequestDiaryDetailsEvent;
import com.zan.diary.events.diary.UpdateDiaryEvent;

public interface DiaryRepository {

	  public DiaryDetailsEvent requestDiaryDetails(RequestDiaryDetailsEvent requestDiaryDetailsEvent);

	  public DiaryCreatedEvent createDiary(CreateDiaryEvent event);

	  public DiaryUpdatedEvent updateDiary(UpdateDiaryEvent updateDiaryEvent);

	  public DiaryDeletedEvent deleteDiary(DeleteDiaryEvent deleteDiaryEvent);
}
