package com.caue.testforstudy.controller;

import com.caue.testforstudy.dto.UserRequestDto;
import com.caue.testforstudy.dto.UserResponseDto;
import com.caue.testforstudy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){

        UserResponseDto userResponse = userService.findById(id);

        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto userRequestDto){

        UserResponseDto userResponse = userService.save(userRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponse);
    }
}
