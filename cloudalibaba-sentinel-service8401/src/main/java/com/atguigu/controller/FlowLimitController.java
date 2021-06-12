package com.atguigu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "======testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "======testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotkey" , blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
     return "--------testHotKey";
    }

//    兜底方法
    public  String deal_testHotKey(String p1, String p2, BlockException exc){
        return "_-----------deal_testHotKey,  /(ㄒoㄒ)/~~";   //sentinel系统默认的提示:BLocked by sentinel (flow limiting)
    }
}
