package com.jimbeam.test.utils.testcontainer.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class PropertyKafkaContainerConfigProvider implements KafkaContainerConfigProvider {

    @Value("${container.kafka.image:confluentinc/cp-kafka:7.7.0}")
    private String image;

}
