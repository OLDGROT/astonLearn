package org.example.astonlearn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.astonlearn.dto.UserDto;
import org.example.astonlearn.mapper.UserMapper;
import org.example.astonlearn.model.Role;
import org.example.astonlearn.model.User;
import org.example.astonlearn.repository.RoleRepository;
import org.example.astonlearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        log.info("Запрос на получение списка всех пользователей");
        List<UserDto> users = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        log.info("Найдено пользователей: {}", users.size());
        return users;
    }

    public UserDto createUser(UserDto dto) {
        log.info("Создание нового пользователя с email: {}", dto.getEmail());

        Role role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(() -> {
                    log.error("Роль '{}' не найдена при создании пользователя", dto.getRoleName());
                    return new RuntimeException("Роль не найдена");
                });

        User user = userMapper.toEntity(dto, role);
        userRepository.save(user);

        log.info("Пользователь '{}' успешно создан с ролью '{}'", user.getUsername(), role.getName());
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UserDto dto) {
        log.info("Обновление пользователя с id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Не удалось обновить — пользователь с id {} не найден", id);
                    return new RuntimeException("Пользователь не найден");
                });

        Role role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(() -> {
                    log.error("Роль '{}' не найдена при обновлении пользователя", dto.getRoleName());
                    return new RuntimeException("Роль не найдена");
                });

        user = userMapper.toEntity(dto, role);
        userRepository.save(user);

        log.info("Пользователь с id {} успешно обновлён", id);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        log.info("Удаление пользователя с id: {}", id);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("Пользователь с id {} успешно удалён", id);
        } else {
            log.warn("Попытка удалить несуществующего пользователя с id {}", id);
        }
    }
}
