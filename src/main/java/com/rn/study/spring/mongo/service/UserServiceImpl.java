package com.rn.study.spring.mongo.service;

import com.rn.study.spring.mongo.dto.PageDto;
import com.rn.study.spring.mongo.dto.UserDto;
import com.rn.study.spring.mongo.model.PagingAndSortingRequest;
import com.rn.study.spring.mongo.model.User;
import com.rn.study.spring.mongo.repository.UserRepository;
import com.rn.study.spring.mongo.util.PageUtil;
import com.rn.study.spring.mongo.util.UserUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDto> save(UserDto userDto) {
        return userRepository.save(UserUtil.map(userDto))
                .map(UserUtil::map);
    }

    @Override
    public Mono<UserDto> get(String id) {
        return userRepository.findById(id)
                .map(UserUtil::map);
    }

    @Override
    public Mono<UserDto> update(String id, UserDto userDto) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    User userRequested = UserUtil.map(userDto);
                    userRequested.setId(user.getId());
                    return userRepository.save(userRequested);
                }).map(UserUtil::map);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<PageDto<User>> findAll(PagingAndSortingRequest request) {
        return userRepository.findWithOptionalParameters(request)
                .map(PageUtil::map);
    }
}
