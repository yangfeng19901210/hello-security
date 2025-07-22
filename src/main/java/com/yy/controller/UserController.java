package com.yy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 ** UserController 普通用户可访问的接口
 ** <br><br>
 ** @ClassName: UserController
 ** @author: yangfeng
 ** @date: 2025/7/22 9:13
 ** @version: 1.0.0
 *********************************************************/
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @GetMapping("/viewAdvert")
    public String viewAdvert(){
        log.info("浏览广告");
        return "浏览广告成功";
    }
}
