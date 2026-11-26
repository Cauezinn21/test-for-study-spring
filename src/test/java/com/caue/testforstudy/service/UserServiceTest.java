package com.caue.testforstudy.service;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.entity.User;
import com.caue.testforstudy.entitymapper.UserMapper;
import com.caue.testforstudy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void shouldSaveUserWithSucess(){

        // Arrange guys
        User user = User.builder()
                .id(1L)
                .name("Cauê")
                .email("caue@gmail.com")
                .created_at(LocalDateTime.of(2026, 11,23,18,45))
                .build();

        UserRequestDto userRequestDto = UserMapper.forRequest(user);

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        // Act beautifuls
        UserResponseDto save = userService.save(userRequestDto);

        // Assertions :
        assertThat(save.name())
                .isEqualTo("Cauê")
                .isNotEmpty()
                .isNotBlank();

        assertThat(save.id())
                .isNotNull();

    }


}