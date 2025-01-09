package com.rn.study.spring.mongo.service;

import com.rn.study.spring.mongo.dto.PageDto;
import com.rn.study.spring.mongo.dto.UserDto;
import com.rn.study.spring.mongo.model.PagingAndSortingRequest;
import com.rn.study.spring.mongo.model.User;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> save(UserDto user);
    Mono<UserDto> get(String id);
    Mono<UserDto> update(String id, UserDto user);
    Mono<Void> delete(String id);
    Mono<PageDto<User>> findAll(PagingAndSortingRequest request);
}
