package com.example.avitobybraincell.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.avitobybraincell.dto.NewPasswordDto;
import com.example.avitobybraincell.dto.UserDto;
import com.example.avitobybraincell.exception.UserUnauthorizedException;
import com.example.avitobybraincell.mapper.UserMapper;
import com.example.avitobybraincell.model.User;
import com.example.avitobybraincell.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       UserDetailsManager manager, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.manager = manager;
        this.encoder = encoder;
    }

    public void setPassword(Authentication auth, NewPasswordDto newPassword) {
        manager.changePassword(newPassword.getCurrentPassword(), encoder.encode(newPassword.getNewPassword()));
    }

    public UserDto updateInfo(Authentication auth, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            throw new UserUnauthorizedException("User not found");
        }
        userMapper.updateUser(userDto, user);
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    public void updateImage(Authentication auth, MultipartFile image) {
        //to be done
    }

    public UserDto findInfo(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName());
        return userMapper.userToUserDto(user);
    }
}
