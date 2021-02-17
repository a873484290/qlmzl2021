package com.baidu.servicebase.exceptionhandler;

import com.baidu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lw
 * @date 2020/12/1
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody  //返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("服务器全局异常");
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseBody  //返回数据
    public R error(NullPointerException e) {
        e.printStackTrace();
        return R.error().message("空指针异常");
    }

    @ExceptionHandler(ZdyException.class)
    @ResponseBody  //返回数据
    private R error(ZdyException e) {
        e.printStackTrace();

        log.error("getMsg-----"+e.getMsg());//  加载日志到文件中
        return R.error().
                code(e.getCode()).message(e.getMsg());
    }
}
