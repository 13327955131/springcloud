package com.atguigu.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "······PaymentFallbackService fall paymentInfo_OK     ``兜底··";
    }

    @Override
    public String timeout(Integer id) {
        return "······PaymentFallbackService fall timeout     ``兜底··";
    }
}
