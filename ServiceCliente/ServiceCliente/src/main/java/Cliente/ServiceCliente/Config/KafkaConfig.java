package Cliente.ServiceCliente.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import Cliente.ServiceCliente.DTO.ClienteEventoDTO;

 @Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, ClienteEventoDTO> kafkaTemplate(
            ProducerFactory<String, ClienteEventoDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}