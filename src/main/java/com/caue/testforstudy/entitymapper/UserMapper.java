package com.caue.testforstudy.entitymapper;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.entity.User;

public class UserMapper {
    public static User forEntity(UserRequestDto dto){
        return User
                .builder()
                .name(dto.name())
                .email(dto.email())
                .build();
    }
    public static UserResponseDto forResponse(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreated_at());
    }
}
