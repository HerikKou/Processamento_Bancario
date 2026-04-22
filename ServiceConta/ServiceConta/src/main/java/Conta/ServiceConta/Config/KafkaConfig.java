package Conta.ServiceConta.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import Conta.ServiceConta.DTO.ContaEventoDTO;

 @Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, ContaEventoDTO> kafkaTemplate(
            ProducerFactory<String, ContaEventoDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}