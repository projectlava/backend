package com.zan.diary.events.diary;

import com.zan.diary.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllDiariesEvent extends ReadEvent {

  private final List<DiaryDetails> diarisDetails;

  public AllDiariesEvent(List<DiaryDetails> orders) {
    this.diarisDetails = Collections.unmodifiableList(orders);
  }

  public Collection<DiaryDetails> getOrdersDetails() {
    return this.diarisDetails;
  }
}
