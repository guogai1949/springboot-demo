package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class RabbitComsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitComsumer.class);

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")
    public void processRabbitQueue(HashMap<String, Object> exportParam, Message message, Channel channel) {
        logger.info("ExportInfoMqListener-exportParam={}", JSONObject.toJSONString(exportParam));

        try {
            logger.info("getMessageProperties={}",message.getMessageProperties().getDeliveryTag() );
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
