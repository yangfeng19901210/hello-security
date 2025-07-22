package com.yy.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*********************************************************
 ** security用户信息
 ** <br><br>
 ** @ClassName: SysUser
 ** @author: yangfeng
 ** @date: 2025/7/22 13:52
 ** @version: 1.0.0
 *********************************************************/
@Data
public class SysUser implements UserDetails {

    private Long userId;

    private String username; // Spring Security认证使用的字段

    private String password;

    private String status; // 状态（0正常 1锁定）

    private String delFlag; // 删除标志（0代表存在 1代表删除）

    private List<String> roles;

    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toSet()));
        authorities.addAll(permissions.stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toSet()));
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return"0".equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return"0".equals(delFlag);
    }

}
