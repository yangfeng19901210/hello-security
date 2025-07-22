package com.yy.controller;

import com.yy.common.Result;
import com.yy.utils.JwtUtils;
import com.yy.vo.in.LoginInVO;
import com.yy.vo.out.LoginSuccessOutVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: LoginController
 ** @author: yangfeng
 ** @date: 2025/7/22 15:53
 ** @version: 1.0.0
 *********************************************************/
@RestController
@Slf4j
public class LoginController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtils jwtUtils;
    @PostMapping("/auth/login")
    public Result<LoginSuccessOutVO> login(@RequestBody LoginInVO loginInVO) {
        log.info("用户登录: {}", loginInVO.getUserName());

        // 1. 认证用户名密码
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInVO.getUserName(), loginInVO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String token = jwtUtils.generateToken(userDetails.getUsername(), roles);
        // 假设登录成功，返回一个成功的响应
        LoginSuccessOutVO successOutVO = new LoginSuccessOutVO();
        successOutVO.setToken(token);
        return Result.success(successOutVO);
    }


}
