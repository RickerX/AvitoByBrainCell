package com.example.avitobybraincell.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}
