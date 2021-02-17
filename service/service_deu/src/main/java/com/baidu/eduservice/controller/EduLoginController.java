package com.baidu.eduservice.controller;

import com.baidu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //跨域
public class EduLoginController {

    @PostMapping("login")
    public R login() {


        return R.ok().data("token", "admin");//
    }


    @GetMapping("info")
    public R info() {


        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1091405991,859863778&fm=26&gp=0.jpg");//
    }

}
