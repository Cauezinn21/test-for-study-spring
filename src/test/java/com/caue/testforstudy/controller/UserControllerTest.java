package com.caue.testforstudy.controller;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest
class UserControllerTest {

    @MockitoBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateUser() throws Exception {

        UserRequestDto userRequestDto = new UserRequestDto("Cauê", "caue@gmail.com");
        UserResponseDto userResponseDto = new UserResponseDto(1L, userRequestDto.name(), userRequestDto.email(), LocalDateTime.now());
        String string = objectMapper.writeValueAsString(userRequestDto);
        Mockito.when(userService.save(Mockito.any(UserRequestDto.class)))
                .thenReturn(userResponseDto);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(string))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Cauê"))
                .andExpect(jsonPath("$.email").value("caue@gmail.com"))
                .andExpect(jsonPath("$.created_at").exists());

        Mockito.verify(userService, Mockito.times(1)).save(Mockito.any(UserRequestDto.class));
    }
}