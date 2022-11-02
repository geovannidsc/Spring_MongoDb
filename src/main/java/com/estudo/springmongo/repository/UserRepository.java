package com.estudo.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estudo.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	

}
