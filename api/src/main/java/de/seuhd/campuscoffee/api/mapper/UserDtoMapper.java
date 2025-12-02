package de.seuhd.campuscoffee.api.mapper;

import org.springframework.stereotype.Component;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;

@Component
public class UserDtoMapper {
    public UserDto toUserDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
            .id(user.id())
            .createdAt(user.createdAt())
            .updatedAt(user.updatedAt())
            .loginName(user.loginName())
            .emailAddress(user.emailAddress())
            .firstName(user.firstName())
            .lastName(user.lastName())
            .build();
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) return null;
        return User.builder()
            .id(userDto.id())
            .createdAt(userDto.createdAt())
            .updatedAt(userDto.updatedAt())
            .loginName(userDto.loginName())
            .emailAddress(userDto.emailAddress())
            .firstName(userDto.firstName())
            .lastName(userDto.lastName())
            .build();
}
}