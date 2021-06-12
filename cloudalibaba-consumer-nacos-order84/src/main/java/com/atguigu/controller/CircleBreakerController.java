package com.atguigu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import com.atguigu.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    public   String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;


    @GetMapping(value = "/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")   //没有配置      配置这个  sentinel 才能检测到
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")   //服务降级  fallback 只负责业务异常
    @SentinelResource(value = "fallback", blockHandler = "blockHandle", fallback = "handlerFallback"
    ,exceptionsToIgnore = {IllegalArgumentException.class})   //服务降级    只负责sentinel 控制台配置违规
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result=  restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if(id ==4){
            throw  new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。。。");
        }else if(result.getData() == null){
            throw  new NullPointerException("NullPointerException, 该iID没有对应记录，空指针异常！！！");
        }
        return  result;
    }

    //本例是fallback 服务降级兜底方法
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment=new Payment(id,"null");
        return new CommonResult(444,"兜底异常 handlerFallback,exception 内容："+ e.getMessage(),payment);
    }

    //本例是blockHandle 限流兜底方法
    public CommonResult blockHandle(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id , "null");
        return new CommonResult(445,"blockHandle-sentinel限流，无此流水：blockException:"+blockException.getMessage(),payment);
    }



    //=============sentinel 整合OpenFeign 使用

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL (@PathVariable("id") Long id){
        //这样就可以直接调用 9003中的接口了  很方便  比ribbon 调用 restTemplate 好用
        return paymentService.paymentSQL(id);
    }





}
