package com.baidu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lw
 * @date 2020/12/1
 **/

@NoArgsConstructor    //生成无参构造方法
@AllArgsConstructor  //有参
@Data
public class ZdyException extends RuntimeException {
    private Integer code;    //状态吗
    private String msg;     //异常信息


}
