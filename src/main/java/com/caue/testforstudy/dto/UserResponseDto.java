package com.caue.testforstudy.dto;

import java.time.LocalDateTime;

public record UserResponseDto (Long id, String name, String email, LocalDateTime created_at){
}
