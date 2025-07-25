package com.yy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 ** AdminController 管理员可访问的接口
 ** <br><br>
 ** @ClassName: HelloController
 ** @author: yangfeng
 ** @date: 2025/7/22 9:13
 ** @version: 1.0.0
 *********************************************************/
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdminController {
    @GetMapping("/queryUser")
    public String queryUser(){
        log.info("query user");
        return "查询用户成功";
    }

    @GetMapping("/queryRole")
//    @PreAuthorize("hasRole('skl')")
//    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admim'") 管理员角色且用户名是admim 方可访问
    public String queryRole(){
        log.info("query role");
        return "查询角色成功";
    }
}
