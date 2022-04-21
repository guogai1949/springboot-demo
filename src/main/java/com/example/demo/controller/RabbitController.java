package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@RestController
@RequestMapping("/rabbit")
public class RabbitController implements RabbitTemplate.ReturnCallback {

    private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack) {
                    logger.info("发送消息成功，{}",correlationData);
                }else {
                    logger.info("发送消息失败，{}",correlationData);
                }
            }
        });
    }

    @GetMapping("/producer")
    public String producer() {
        logger.info("rabbitmq producer start !!!");
        HashMap<String, Object> exportParam = new HashMap<String, Object>();
        exportParam.put("key","value");
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRoutingKey",exportParam);
        return "success";
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("rabbitmq producer start !!!");
    }
}
