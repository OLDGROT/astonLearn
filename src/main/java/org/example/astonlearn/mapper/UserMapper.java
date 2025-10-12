package org.example.astonlearn.mapper;

import org.example.astonlearn.dto.UserDto;
import org.example.astonlearn.model.Role;
import org.example.astonlearn.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto dto, Role role) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .age(dto.getAge())
                .role(role)
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .roleName(user.getRole().getName()) // <-- строка
                .build();
    }
}
