package com.zan.diary.persistence.integration;


import com.mongodb.Mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.zan.diary.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.zan.diary.persistence.domain.fixture.PersistenceFixture.standardItem;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {CoreConfig.class, MVCConfig.class})
// {!begin top}
public class DiaryMappingIntegrationTests {

  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "diary"));

    mongo.dropCollection("diary");
  }

  @After
  public void teardown() {
    mongo.dropCollection("diary");
  }

  @Test
  public void thatItemIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {

    mongo.insert(standardItem());

    assertEquals(1, mongo.getCollection("diary").count());

    assertTrue(usingMongo(mongo).collection("diary").hasIndexOn("_id"));
    assertTrue(usingMongo(mongo).collection("diary").hasIndexOn("diaryTitle"));
  }

  @Test
  public void thatItemCustomMappingWorks() throws Exception {
    mongo.insert(standardItem());

    assertTrue(usingMongo(mongo).collection("diary").first().hasField("diaryTitle"));
  }
}
// {!end top}
