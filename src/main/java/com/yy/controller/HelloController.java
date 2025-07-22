package com.yy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 ** HelloController 验证服务可用性
 ** <br><br>
 ** @ClassName: HelloController
 ** @author: yangfeng
 ** @date: 2025/7/22 9:13
 ** @version: 1.0.0
 *********************************************************/
@RequestMapping("/hello")
@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/first")
    public String first() {
        log.info("first hello");
        return "Hello, Security!";
    }
}
