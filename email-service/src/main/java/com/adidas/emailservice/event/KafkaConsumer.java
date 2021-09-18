package com.adidas.emailservice.event;

import com.adidas.emailservice.domain.service.EmailService;
import com.adidas.emailservice.dto.EmailDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 03:14
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final String KAFKA_TOPIC = "subscription-system-subscribe-successful";
    private final String KAFKA_GROUP_ID = "group_id";
    private final EmailService emailService;

    @KafkaListener(topics = KAFKA_TOPIC, groupId = KAFKA_GROUP_ID)
    public void consumer(EmailDTO emailDTO) {
        log.info("Consuming from Kafka");
        emailService.sendEmail(emailDTO);
    }
}
