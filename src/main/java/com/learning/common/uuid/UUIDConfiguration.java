package com.learning.common.uuid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class UUIDConfiguration {

    @Bean
    @ConditionalOnMissingBean(UUIDGenerator.class)
    public UUIDGenerator randomUUIDGenerator(){
        log.info("creating randon UUID generator");
        return new RandomUUIDGenerator();
    }
}
