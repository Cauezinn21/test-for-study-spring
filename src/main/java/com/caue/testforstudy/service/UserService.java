package com.caue.testforstudy.service;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.entity.User;
import com.caue.testforstudy.entitymapper.UserMapper;
import com.caue.testforstudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<UserResponseDto> save(UserRequestDto userRequestDto){
        User user = UserMapper.forEntity(userRequestDto);

        User saved = userRepository.save(user);

        UserResponseDto userResponseDto = UserMapper.forResponse(saved);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userResponseDto);
    }
}
