package Transacao.ServiceTransacao.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import Transacao.ServiceTransacao.DTO.TransacaoEventoDTO;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, TransacaoEventoDTO> kafkaTemplate(
            ProducerFactory<String, TransacaoEventoDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}