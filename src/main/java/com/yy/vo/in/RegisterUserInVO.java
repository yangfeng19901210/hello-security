package com.yy.vo.in;

import lombok.Data;

/*********************************************************
 ** 用户注册请求参数
 ** <br><br>
 ** @ClassName: RegisterUserInVO
 ** @author: yangfeng
 ** @date: 2025/7/22 17:19
 ** @version: 1.0.0
 *********************************************************/
@Data
public class RegisterUserInVO {
    private String userName;

    private String password;
}
