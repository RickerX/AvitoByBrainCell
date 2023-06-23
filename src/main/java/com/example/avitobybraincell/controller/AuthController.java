package com.example.avitobybraincell.controller;

import com.example.avitobybraincell.dto.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.avitobybraincell.dto.LoginRequestDto;
import com.example.avitobybraincell.dto.RegisterReqDto;
import com.example.avitobybraincell.service.AuthService;

import com.example.avitobybraincell.dto.Role;
import static com.example.avitobybraincell.dto.Role.USER;


/**
 * Контроллер обработки запросов аутентификации и регистрации новых пользователей.
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация/Авторизация")


public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
        @Operation(summary = "Авторизация пользователя", responses = {
                @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
                @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())})}
        )
        public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
            if (authService.login(req.getUsername(), req.getPassword())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

            @PostMapping("/register")
            @Operation(summary = "Регистрация пользователя", responses = {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})}
            )
            public ResponseEntity<?> register (@RequestBody RegisterReqDto req){
                Role role = req.getRole() == null ? USER : Role.valueOf(req.getRole());
                if (authService.register(req, role)) {
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
        }


