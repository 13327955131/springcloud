package com.atguigu.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Data
public class ConfigData {
    @Value("${config.port}")
    private  String confidInfo;
}
