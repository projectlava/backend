package com.zan.diary.persistence.repository;

import com.zan.diary.persistence.domain.PersistenceDiary;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface DiaryRepository extends CrudRepository<PersistenceDiary, String> {
	PersistenceDiary findByid(UUID id);
	List<PersistenceDiary> findBySubject(String subject);
}
