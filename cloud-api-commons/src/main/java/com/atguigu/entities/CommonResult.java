package com.atguigu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //404  not_found
    //状态码
    private Integer code;

    //消息
    private String message;

    private T data;



    public  CommonResult(Integer code, String message){
        this(code,message,null);
    }


}
