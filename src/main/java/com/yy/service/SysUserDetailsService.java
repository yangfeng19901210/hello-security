package com.yy.service;

import com.yy.pojo.SysUser;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*********************************************************
 **
 ** <br><br>
 ** @ClassName: SysUserDetailsService
 ** @author: yangfeng
 ** @date: 2025/7/22 13:59
 ** @version: 1.0.0
 *********************************************************/
@Service
public class SysUserDetailsService implements UserDetailsService {

    private static final List<SysUser> userList = new ArrayList<>();
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser u = userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
        if (null == u) {
            throw new UsernameNotFoundException(username);
        }
        return u;
    }
    @PostConstruct
    private void init(){
        SysUser user1 = new SysUser();
        user1.setUsername("lisi");
        user1.setPassword(passwordEncoder.encode("lisi"));
        user1.setStatus("0");
        user1.setDelFlag("0");
        user1.setRoles(Arrays.asList("ADMIN"));
        user1.setPermissions(Arrays.asList("admin:save","admin:update","ROLE_ADMIN"));
        userList.add(user1);
    }
}
