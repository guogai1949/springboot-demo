package com.example.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.common.anotations.TestAnnotation;
import com.example.demo.common.anotations.TestAnnotation1;
import com.example.demo.config.TestConfiguration;
import com.example.demo.filter.GlobelInterceptor;
import com.example.demo.model.User;
import com.example.demo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/home")
@EnableConfigurationProperties(TestConfiguration.class)
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @NacosValue(value = "${nacos.test.propertie:123}", autoRefreshed = true)
    private String testProperties;

    @Autowired
    private TestConfiguration testConfiguration;

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/index")
    @TestAnnotation(args = {"test1","test2"})
    public String index() {
        logger.info("调用方法index()");
        return "hello world!!!";
    }


    @GetMapping("/test")
    @TestAnnotation1
    public String test(){
        logger.info("调用方法test()");
        return testProperties;
    }

    @GetMapping("async")
    public String async() {
        asyncService.asyncExecute();
        logger.info("调用async方法");
        return "sucess";
    }

    @PostMapping("/test-post")
    public String testPost(@Valid @RequestBody User user) {
        System.out.println(testConfiguration.getName());
        System.out.println("random:" + ThreadLocalRandom.current().nextInt(10));
        return "post-seccess";
    }
}
