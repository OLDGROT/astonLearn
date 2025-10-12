package org.example.astonlearn.repository;


import org.example.astonlearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}