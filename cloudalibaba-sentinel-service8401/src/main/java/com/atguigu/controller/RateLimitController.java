package com.atguigu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import com.atguigu.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping( "/rateLimit/customerBlockHand1er")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {

        int i = 10/0;
        return new CommonResult(200, "按客户自定义处理异常", new Payment(2020L, "serialoe03"));
    }

}
