package com.yy.vo.in;

import lombok.Data;

/*********************************************************
 ** 登录请求参数
 ** <br><br>
 ** @ClassName: LoginInVO
 ** @author: yangfeng
 ** @date: 2025/7/22 15:56
 ** @version: 1.0.0
 *********************************************************/
@Data
public class LoginInVO {
    private String userName;

    private String password;
}
