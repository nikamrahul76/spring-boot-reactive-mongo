package com.rn.study.spring.mongo.config;

import com.rn.study.spring.mongo.repository.CustomReactiveRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
@Configuration
@EnableReactiveMongoRepositories(
        basePackages = "com.rn.study.spring.mongo.repository",
        repositoryFactoryBeanClass = CustomReactiveRepositoryFactoryBean.class
)
public class MongoConfig {
    // Additional MongoDB-related beans can go here
}