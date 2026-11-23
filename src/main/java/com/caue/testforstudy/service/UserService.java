package com.caue.testforstudy.service;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.entity.User;
import com.caue.testforstudy.entitymapper.UserMapper;
import com.caue.testforstudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(UserRequestDto userRequestDto){
        User user = UserMapper.forEntity(userRequestDto);

        User saved = userRepository.save(user);

        return UserMapper.forResponse(saved);
    }
}
