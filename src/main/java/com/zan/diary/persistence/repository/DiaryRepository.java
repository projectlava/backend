package com.zan.diary.persistence.repository;

import com.zan.diary.persistence.domain.Diary;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DiaryRepository extends CrudRepository<Diary, String> {
	Diary findByid(UUID id);
	List<Diary> findBySubject(String subject);
}
