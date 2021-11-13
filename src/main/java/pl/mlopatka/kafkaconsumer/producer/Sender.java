package pl.mlopatka.kafkaconsumer.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static pl.mlopatka.kafkaconsumer.consumer.KafkaConsumer.INCOMING_TRANSPORT;

@Component
@RequiredArgsConstructor
public class Sender {

    private final KafkaTemplate<String, String> template;

    @PostConstruct
    private void sendOnStartup() {
        template.send(INCOMING_TRANSPORT, "transport-test-data");
    }
}
