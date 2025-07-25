package com.yy.pojo;

import lombok.Data;

import java.util.List;

/*********************************************************
 ** security用户信息
 ** <br><br>
 ** @ClassName: SysUser
 ** @author: yangfeng
 ** @date: 2025/7/22 13:52
 ** @version: 1.0.0
 *********************************************************/
@Data
public class SysUser {

    private Long userId;

    private String username; // Spring Security认证使用的字段

    private String password;

    private String status; // 状态（0正常 1锁定）

    private String delFlag; // 删除标志（0代表存在 1代表删除）

    private List<String> roles;

    private List<String> permissions;


}
