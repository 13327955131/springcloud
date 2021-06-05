package com.atguigu.controller;

import com.atguigu.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")       //全局 hystrix 配置默认的兜底方法  不需要每个方法单独配置
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //兜底的方法   程序超时 或者 程序报错都是走兜底方法
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            //这个线程的最大超时时间 3 秒钟  3秒内处理不了 那么就调用兜底方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds" , value="5000")
    })
    public String timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.timeout(id);
    }



    public String paymentTimeOutFallbackMethod(@PathVariable("id")  Integer id){
        return  "我是消费者80，对方支付系统繁忙，请10秒钟后再试或者自己运行出错，请检查自己。";
    }


    //  测试全局 兜底方法
    @GetMapping("/consumer/hystrix/default")
    @HystrixCommand
    public String quanjuTest(){
        int x=10/0;
        return "111111111";
    }
//     下面是全局的 hustrix fallback方法
    public String payment_Global_FallbackMethod(){
        return  "Global异常处理信息，请稍后再试。。。。。";
    }

}
