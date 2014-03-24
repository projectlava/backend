package com.zan.diary.persistence.repository;

import com.zan.diary.persistence.domain.PersistenceUser;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<PersistenceUser, String> {
	PersistenceUser findByid(long id);
	PersistenceUser findByName(String name);
	List<PersistenceUser> findByloc(String loc);
}
