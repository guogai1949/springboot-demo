package com.example.demo.service.impl;

import com.example.demo.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("taskExecutor")
    public void asyncExecute() {
        try {
            log.info("当前线程[{}] sleep 3000ms",Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
    }
}
