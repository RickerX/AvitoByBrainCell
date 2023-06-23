package com.example.avitobybraincell.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperUser {
    private int count;
    private List<UserDto> results;
}
