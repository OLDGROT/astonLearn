package org.example.astonlearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.astonlearn.dto.UserDto;
import org.example.astonlearn.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Получен запрос на получение всех пользователей");
        List<UserDto> users = userService.getAllUsers();
        log.info("Возвращено {} пользователей", users.size());
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        log.info("Получен запрос на создание пользователя с email: {}", dto.getEmail());
        UserDto created = userService.createUser(dto);
        log.info("Пользователь '{}' успешно создан с ролью '{}'", created.getUsername(), created.getRoleName());
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {
        log.info("Получен запрос на обновление пользователя с id: {}", id);
        UserDto updated = userService.updateUser(id, dto);
        log.info("Пользователь с id {} успешно обновлён", id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя с id: {}", id);
        userService.deleteUser(id);
        log.info("Пользователь с id {} успешно удалён", id);
        return ResponseEntity.noContent().build();
    }
}
