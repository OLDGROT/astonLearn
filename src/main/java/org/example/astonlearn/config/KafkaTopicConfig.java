package org.example.astonlearn.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
    @Bean
    public NewTopic userDeleteTopic() {
        return TopicBuilder.name("user-delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userCreateTopic() {
        return TopicBuilder.name("user-create")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
