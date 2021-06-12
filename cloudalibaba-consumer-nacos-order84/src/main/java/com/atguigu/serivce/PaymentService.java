package com.atguigu.serivce;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    /**
     * 这里的接口就是把要调用的服务端接口拿过来即可   然后就可以当普通serivce 使用
      * @param id
     * @return
     */

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
