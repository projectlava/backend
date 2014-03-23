package com.zan.diary.persistence.repository;

import com.zan.diary.persistence.domain.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends CrudRepository<User, String> {
	User findByid(UUID uuid);
	User findByName(String name);
	List<User> findByloc(String loc);
}
