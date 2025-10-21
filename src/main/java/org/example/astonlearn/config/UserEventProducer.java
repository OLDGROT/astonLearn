package org.example.astonlearn.config;

public interface UserEventProducer {
    void sendUserCreate(Long userId);
    void sendUserDelete(Long userId);
}
