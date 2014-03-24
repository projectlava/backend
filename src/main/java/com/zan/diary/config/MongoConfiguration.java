package com.zan.diary.config;

import com.mongodb.Mongo;
import com.zan.diary.persistence.repository.DiaryRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.zan.diary.persistence.repository",
      includeFilters = @ComponentScan.Filter(value = {DiaryRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class MongoConfiguration {

  public @Bean
  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
    return new MongoTemplate(mongo, "diary");
  }

  public @Bean Mongo mongo() throws UnknownHostException {
    return new Mongo("localhost");
  }
}
