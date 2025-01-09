package com.rn.study.spring.mongo.repository;

import com.rn.study.spring.mongo.model.PagingAndSortingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public class CustomReactiveRepositoryImpl<T, ID extends Serializable> extends SimpleReactiveMongoRepository<T, ID>
        implements CustomReactiveRepository<T, ID> {

    private final ReactiveMongoOperations reactiveMongoOperations;
    private final MongoEntityInformation<T, ID> entityInformation;

    public CustomReactiveRepositoryImpl(MongoEntityInformation<T, ID> entityInformation
            , ReactiveMongoOperations reactiveMongoOperations) {
        super(entityInformation, reactiveMongoOperations);
        this.entityInformation = entityInformation;
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Page<T>> findWithOptionalParameters(PagingAndSortingRequest request) {
        long page = request.getPage() == null ? 0 : request.getPage() - 1;
        long limit = request.getLimit() == null ? Long.MAX_VALUE : request.getLimit();

        Sort.Direction direction = Objects.isNull(request.getSortType())
                ? Sort.Direction.ASC : Sort.Direction.fromString(request.getSortType());

        PageRequest pageRequest = PageRequest.of(
                (int) page, (int) limit,
                Sort.by(direction, request.getSortBy() == null ? "id" : request.getSortBy())
        );

        Query query = new Query();
        request.getFields().forEach((key, value) -> {
            if (value instanceof String) {
                query.addCriteria(Criteria.where(key).regex(
                        Pattern.compile((String) value, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
            } else {
                query.addCriteria(Criteria.where(key).is(value));
            }
        });

        query.with(pageRequest);
        Flux<T> contentFlux = reactiveMongoOperations.find(query, entityInformation.getJavaType());
        Mono<Long> totalMono = reactiveMongoOperations.count(query, entityInformation.getJavaType());

        return contentFlux.collectList()
                .zipWith(totalMono)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageRequest, tuple.getT2()));
    }
}
