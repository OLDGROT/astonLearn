package org.example.astonlearn.mapper;

import org.example.astonlearn.dto.UserDto;
import org.example.astonlearn.model.Role;
import org.example.astonlearn.model.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.name", target = "role")
    UserDto toDto(User user);
    @Mapping(source = "role", target = "role")
    User toEntity(UserDto userDto,@Context Role role);

    default Role map(String roleName) {
        if (roleName == null) return null;
        Role role = new Role();
        role.setName(roleName);
        return role;
    }
}
