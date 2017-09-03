package com.microserices.checkout.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(KafkaChannels.class)
public class KafkaConsumerConfig {
}
