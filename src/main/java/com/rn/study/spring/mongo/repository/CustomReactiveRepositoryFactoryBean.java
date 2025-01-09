package com.rn.study.spring.mongo.repository;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.NonNull;

import java.io.Serializable;

public class CustomReactiveRepositoryFactoryBean<T, ID extends Serializable>
        extends ReactiveMongoRepositoryFactoryBean<CustomReactiveRepository<T, ID>, T, ID> {

    public CustomReactiveRepositoryFactoryBean(Class<? extends CustomReactiveRepository<T, ID>> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected @NonNull RepositoryFactorySupport getFactoryInstance(@NonNull ReactiveMongoOperations operations) {
        return new CustomReactiveRepositoryFactory<>(operations);
    }

    private static class CustomReactiveRepositoryFactory <T, I extends Serializable> extends ReactiveMongoRepositoryFactory {

        private final ReactiveMongoOperations reactiveMongoOperations;

        public CustomReactiveRepositoryFactory(ReactiveMongoOperations reactiveMongoOperations) {
            super(reactiveMongoOperations);
            this.reactiveMongoOperations = reactiveMongoOperations;
        }

        @Override
        protected @NonNull Object getTargetRepository(RepositoryInformation information) {
            MongoEntityInformation<?, I> entityInformation = getEntityInformation(information.getDomainType());
            return new CustomReactiveRepositoryImpl<>(entityInformation, reactiveMongoOperations);
        }

        @Override
        protected @NonNull Class<?> getRepositoryBaseClass(@NonNull RepositoryMetadata metadata) {
            return CustomReactiveRepositoryImpl.class;
        }
    }
}
