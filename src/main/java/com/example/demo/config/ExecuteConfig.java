package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableConfigurationProperties(ExecutorConfiguration.class)
@Slf4j
public class ExecuteConfig {


    @Autowired
    private ExecutorConfiguration executorConfiguration;

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(executorConfiguration.getCorePoolSize());
        executor.setMaxPoolSize(executorConfiguration.getMaxPoolSize());
        executor.setQueueCapacity(executorConfiguration.getQueueCapacity());
        executor.setThreadNamePrefix(executorConfiguration.getThreadName());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
