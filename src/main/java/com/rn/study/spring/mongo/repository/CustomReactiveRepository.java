package com.rn.study.spring.mongo.repository;

import com.rn.study.spring.mongo.model.PagingAndSortingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomReactiveRepository<T, ID extends Serializable> extends ReactiveMongoRepository<T, ID> {
    Mono<Page<T>> findWithOptionalParameters(PagingAndSortingRequest request);
}