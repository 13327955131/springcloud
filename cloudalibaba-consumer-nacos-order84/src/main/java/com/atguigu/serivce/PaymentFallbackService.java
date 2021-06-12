package com.atguigu.serivce;

import com.atguigu.entities.CommonResult;
import com.atguigu.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(4444444,"服务降级返回，-----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
