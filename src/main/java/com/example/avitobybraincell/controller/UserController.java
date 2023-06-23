package com.example.avitobybraincell.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.avitobybraincell.dto.NewPasswordDto;
import com.example.avitobybraincell.dto.UserDto;
import com.example.avitobybraincell.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("users")
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", responses = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())})}
    )
    public ResponseEntity<?> setPassword(Authentication auth, @RequestBody NewPasswordDto newPassword) {
        userService.setPassword(auth, newPassword);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновить информацию об авторизованном пользователю", responses = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(
                    implementation = UserDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})}
    )
    public ResponseEntity<UserDto> updateInfo(Authentication auth, @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateInfo(auth, user));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить аватар авторизованного пользователя", responses = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})}
    )
    public ResponseEntity<?> updateImage(Authentication auth, @RequestParam("image") MultipartFile image) {
        userService.updateImage(auth, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе", responses = {
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(
                    implementation = UserDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema())})}
    )
    public ResponseEntity<UserDto> findInfo(Authentication auth) {
        return ResponseEntity.ok(userService.findInfo(auth));
    }
}
