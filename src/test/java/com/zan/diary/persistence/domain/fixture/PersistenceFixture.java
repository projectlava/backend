package com.zan.diary.persistence.domain.fixture;


import com.zan.diary.persistence.domain.PersistenceDiary;
import com.zan.diary.persistence.domain.PersistenceUser;

import java.util.*;

public class PersistenceFixture {

	public static PersistenceDiary standardItem() {
		PersistenceDiary item = new PersistenceDiary();

		item.setDateTimeOfCreation(new Date());
		item.setDateTimeOfModification(new Date());
		item.setid(UUID.randomUUID());
		item.setOwner("Daniel");
		item.setPublicDiary(true);
		item.setSubject("subject");
		item.setText("text");
		item.setTitle("title");
		return item;
	}

	public static PersistenceDiary yunNan() {
		PersistenceDiary item = new PersistenceDiary();

		item.setDateTimeOfCreation(new Date());
		item.setDateTimeOfModification(new Date());
		item.setid(UUID.randomUUID());
		item.setOwner("Daniel");
		item.setPublicDiary(true);
		item.setSubject("travel");
		item.setText("text1");
		item.setTitle("title1");
		return item;
	}

	public static PersistenceUser standardUser() {
		PersistenceUser user = new PersistenceUser();
		user.setCode("8888");
		user.setid(19821);
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15213");
		user.setName("Daniel");
		user.setPass("test");
		user.setUserDiaries(new ArrayList<String>());

		return user;
	}

	public static PersistenceUser nastyUser() {
		PersistenceUser user = new PersistenceUser();
		user.setCode("8888");
		user.setid(12112);
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15219");
		user.setName("nasty");
		user.setPass("test");
		user.setUserDiaries(new ArrayList<String>());

		return user;
	}

	public static PersistenceUser niceUser() {
		PersistenceUser user = new PersistenceUser();
		user.setCode("8888");
		user.setid(121);
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15213");
		user.setName("Nice");
		user.setPass("test");
		user.addUserDiary(UUID.randomUUID().toString());

		return user;
	}

}
