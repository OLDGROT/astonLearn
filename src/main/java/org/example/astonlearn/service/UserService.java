package org.example.astonlearn.service;

import lombok.RequiredArgsConstructor;

import org.example.astonlearn.dto.UserDto;
import org.example.astonlearn.model.Role;
import org.example.astonlearn.model.User;
import org.example.astonlearn.repository.RoleRepository;
import org.example.astonlearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto dto) {
        Role role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .age(dto.getAge())
                .role(role)
                .build();

        userRepository.save(user);
        return mapToDto(user);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        Role role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        userRepository.save(user);
        return mapToDto(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .roleName(user.getRole().getName())
                .build();
    }
}

