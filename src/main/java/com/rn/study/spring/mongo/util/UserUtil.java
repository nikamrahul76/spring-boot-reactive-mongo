package com.rn.study.spring.mongo.util;

import com.rn.study.spring.mongo.dto.UserDto;
import com.rn.study.spring.mongo.model.User;

public class UserUtil {
    private UserUtil() {

    }

    public static UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        return userDto;
    }

    public static User map(UserDto userDto) {
        User user= new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        return user;
    }
}
