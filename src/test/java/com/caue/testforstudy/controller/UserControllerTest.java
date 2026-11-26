package com.caue.testforstudy.controller;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockitoBean
    UserService userService;

    @Autowired
    MockMvcTester mockMvcTester;

    @Autowired
    ObjectMapper objectMapper;

    UserResponseDto userResponseDto;

    @BeforeEach
    void setUp(){
        userResponseDto = new UserResponseDto(1L, "Cauê", "caue@gmail.com", LocalDateTime.of(2026, 11,25, 19, 45));
    }

    @Test
    void shouldCreateUser(){

        UserRequestDto userRequestDto = new UserRequestDto("Cauê", "caue@gmail.com");

        given(userService.save(any(UserRequestDto.class))).willReturn(userResponseDto);

        var request = mockMvcTester.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto));

        assertThat(request).hasStatus(HttpStatus.CREATED)
                .bodyJson()
                .isEqualTo(objectMapper.writeValueAsString(userResponseDto));

        then(userService).should().save(userRequestDto);

    }
    @Test
    void  shouldReturnBadRequestWhenNameIsBlank(){

        UserRequestDto userRequestDto = new UserRequestDto("", "caue@gmail.com");

        var request = mockMvcTester.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto));

        assertThat(request).hasStatus(HttpStatus.BAD_REQUEST);

        then(userService).shouldHaveNoInteractions();

    }
    @Test
    void shouldFindUserById(){

        given(userService.findById(1L)).willReturn(userResponseDto);

        var request = mockMvcTester.get().uri("/users/{id}", 1L);

        assertThat(request).hasStatus(HttpStatus.OK)
                        .bodyJson()
                                .isEqualTo(objectMapper.writeValueAsString(userResponseDto));

        then(userService).should().findById(1L);
    }

}