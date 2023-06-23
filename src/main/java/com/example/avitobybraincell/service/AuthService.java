package com.example.avitobybraincell.service;
import com.example.avitobybraincell.dto.RegisterReqDto;
import com.example.avitobybraincell.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDto registerReq, Role role);
}

