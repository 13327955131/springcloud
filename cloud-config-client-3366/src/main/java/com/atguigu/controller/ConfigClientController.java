package com.atguigu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConfigClientController {
    @Resource
    private  ConfigData configData;

    @GetMapping("/configInfo")
    private  String getConfidInfo(){
        log.info("11111111111111:"+configData.getConfidInfo());
        return configData.getConfidInfo();
    }
}
