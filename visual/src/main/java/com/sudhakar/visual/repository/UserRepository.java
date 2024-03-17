package com.sudhakar.visual.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sudhakar.visual.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    boolean existsByEmail(String string);

}
