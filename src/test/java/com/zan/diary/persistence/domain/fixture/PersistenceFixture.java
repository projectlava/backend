package com.zan.diary.persistence.domain.fixture;


import com.zan.diary.persistence.domain.Diary;
import com.zan.diary.persistence.domain.User;

import java.util.*;

public class PersistenceFixture {

	public static Diary standardItem() {
		Diary item = new Diary();

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

	public static Diary yunNan() {
		Diary item = new Diary();

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

	public static User standardUser() {
		User user = new User();
		user.setCode("8888");
		user.setid(UUID.randomUUID());
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15213");
		user.setName("Daniel");
		user.setPass("test");
		user.setUserDiaries(new ArrayList<String>());

		return user;
	}

	public static User nastyUser() {
		User user = new User();
		user.setCode("8888");
		user.setid(UUID.randomUUID());
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15219");
		user.setName("nasty");
		user.setPass("test");
		user.setUserDiaries(new ArrayList<String>());

		return user;
	}

	public static User niceUser() {
		User user = new User();
		user.setCode("8888");
		user.setid(UUID.randomUUID());
		user.setDateTimeOfCreation(new Date());
		user.setLoc("15213");
		user.setName("Nice");
		user.setPass("test");
		user.addUserDiary(UUID.randomUUID().toString());

		return user;
	}

}
