package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${config.port}")
    private  String confidInfo;

    @GetMapping("/configInfo")
    private  String getConfidInfo(){
        return confidInfo;
    }
}
