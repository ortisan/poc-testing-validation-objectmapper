package com.ortiz.kafka.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class KafkaMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    private CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${kafka.topic-name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenMessage(String message) {
        logger.info("Received Message: " + message);
        latch.countDown();
    }
}