package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "executor.thread")
@Data
public class ExecutorConfiguration {

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private String threadName;

}
