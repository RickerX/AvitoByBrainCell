package com.example.avitobybraincell.model;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import javax.management.relation.Role;


@Data
@Entity(name = "users")
public class User {
    @Id
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private String username;
    private String password;
}