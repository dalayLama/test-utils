package com.jimbeam.test.utils.testcontainer.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class KafkaContainerConfig {

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer(KafkaContainerConfigProvider configProvider){
        DockerImageName dockerImageName = DockerImageName.parse(configProvider.getImage());
        return new KafkaContainer(dockerImageName);
    }

    @ConditionalOnMissingBean
    @Bean
    KafkaContainerConfigProvider kafkaContainerConfigProvider() {
        return new PropertyKafkaContainerConfigProvider();
    }

}
