package com.rn.study.spring.mongo.repository;

import com.rn.study.spring.mongo.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends CustomReactiveRepository<User, String> {
    Mono<User> findByEmail(String email);
}
