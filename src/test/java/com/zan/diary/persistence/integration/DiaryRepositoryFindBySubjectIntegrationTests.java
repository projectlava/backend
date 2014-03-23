package com.zan.diary.persistence.integration;


import com.zan.diary.config.MongoConfiguration;
import com.zan.diary.persistence.domain.Diary;
import com.zan.diary.persistence.repository.DiaryRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.zan.diary.persistence.domain.fixture.PersistenceFixture.yunNan;
import static com.zan.diary.persistence.domain.fixture.PersistenceFixture.standardItem;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class DiaryRepositoryFindBySubjectIntegrationTests {

  @Autowired
  DiaryRepository diaryRepository;

  @Autowired
  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo.dropCollection("diary");
  }

  @After
  public void teardown() {
    mongo.dropCollection("diary");
  }

  @Test
  public void thatItemIsInsertedIntoRepoWorks() throws Exception {

    diaryRepository.save(standardItem());
    diaryRepository.save(standardItem());
    diaryRepository.save(yunNan());

    List<Diary> diaryItems = diaryRepository.findBySubject("travel");

    assertEquals(1, diaryItems.size());
  }
}
