package com.example.avitobybraincell.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.avitobybraincell.dto.CreateAdsDto;
import com.example.avitobybraincell.dto.UserDto;
import com.example.avitobybraincell.model.Advert;
import com.example.avitobybraincell.model.User;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(UserDto userDto, @MappingTarget User user);

}
