package pl.mlopatka.kafkaconsumer.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConsumer {

    public static final String INCOMING_TRANSPORT = "incomingTransport";
    private static final String TRANSPORT_LISTENER = "transportListener";
    private static final String DUPLICATED_TRANSPORT_LISTENER = "transportListener2";

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(INCOMING_TRANSPORT)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = TRANSPORT_LISTENER, topics = INCOMING_TRANSPORT)
    public void transportListener(String incomingTransport) {
        System.out.println(incomingTransport);
    }

    // Order was not kept
    @KafkaListener(id = DUPLICATED_TRANSPORT_LISTENER, topics = INCOMING_TRANSPORT)
    public void listen(String incomingTransport) {
        System.out.println(incomingTransport);
    }
}
