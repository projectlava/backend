package com.zan.diary.persistence.integration;


import com.zan.diary.config.JPAConfiguration;
import com.zan.diary.persistence.domain.PersistenceUser;
import com.zan.diary.persistence.domain.fixture.PersistenceFixture;
import com.zan.diary.persistence.repository.UsersRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UsersRepositoryFindUsersContainingTests {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  EntityManager entityManager;

  @Test
  public void thatSearchingForOrdesContainingWorks() throws Exception {

    usersRepository.save(PersistenceFixture.standardUser());
    usersRepository.save(PersistenceFixture.nastyUser());
    usersRepository.save(PersistenceFixture.niceUser());
    
    Iterable<PersistenceUser> allusers = usersRepository.findAll();
    
    List<PersistenceUser> retrievedUsers = usersRepository.findByloc("15213");

    assertNotNull(retrievedUsers);
    assertEquals(2, retrievedUsers.size());

    PersistenceUser usr = usersRepository.findByName("nasty");

    assertNotNull(usr);
  }

}

