package com.ives.springcloud.controller;

import com.ives.springcloud.entities.CommonResult;
import com.ives.springcloud.entities.Payment;
import com.ives.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("======插入結果： "+result);

        if(result>0){
            return new CommonResult(200,"插入數據成功",result);
        }else{
            return new CommonResult(404,"插入數據失敗",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("======查詢結果： "+payment);

        if(payment != null){
            return new CommonResult(200,"查詢成功\t"+"，恭喜您!!!!",payment);
        }else{
            return new CommonResult(404,"沒有對應記錄，查詢失敗，查詢id: "+id,null);
        }
    }

}
