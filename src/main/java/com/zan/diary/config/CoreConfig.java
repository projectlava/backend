package com.zan.diary.config;


import com.zan.diary.core.services.DiaryEventHandler;
import com.zan.diary.core.services.DiaryService;
import com.zan.diary.core.services.UserEventHandler;
import com.zan.diary.core.services.UserService;
import com.zan.diary.persistence.repository.DiaryRepository;
import com.zan.diary.persistence.repository.UsersRepository;
import com.zan.diary.persistence.services.DiaryPersistenceEventHandler;
import com.zan.diary.persistence.services.DiaryPersistenceService;
import com.zan.diary.persistence.services.UserPersistenceEventHandler;
import com.zan.diary.persistence.services.UserPersistenceService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CoreConfig {


  @Bean
  public UserService createUserService(UserPersistenceService userPersistenceService) {
    return new UserEventHandler(userPersistenceService);
  }  
  
  
  @Bean
  public UserPersistenceService creatUserRehandler(UsersRepository usersRepository) {
	  return new UserPersistenceEventHandler(usersRepository);
  }
  
  @Bean
  public DiaryService createDiaryService(DiaryPersistenceService diaryPersistenceService) {
    return new DiaryEventHandler(diaryPersistenceService);
  }
  
  @Bean
  public DiaryPersistenceService creatDiaryRehandler(DiaryRepository diaryRepository) {
	  return new DiaryPersistenceEventHandler(diaryRepository);
  }
  
}
