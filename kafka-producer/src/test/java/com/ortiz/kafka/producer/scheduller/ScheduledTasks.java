package com.ortiz.kafka.producer.scheduller;

import com.ortiz.kafka.producer.KafkaMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private KafkaMessageProducer messageProducer;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        logger.info("Sending event kafka...");
        messageProducer.sendMessage("My Message " + LocalDateTime.now().toString());
    }
}
