package Conta.ServiceConta.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topicConta() {
        return TopicBuilder.name("conta_criada")
                .partitions(2)
                .replicas(1)
                .build();
    }

    
}