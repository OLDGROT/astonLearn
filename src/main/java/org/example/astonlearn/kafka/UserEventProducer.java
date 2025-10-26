package org.example.astonlearn.kafka;

public interface UserEventProducer {
    void sendUserCreate(Long userId);
    void sendUserDelete(Long userId);
}
