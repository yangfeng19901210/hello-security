package com.yy.config;

import com.yy.filter.JwtAuthFilter;
import com.yy.service.SysUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*********************************************************
 ** security配置文件
 ** <br><br>
 ** @ClassName: SecurityConfig
 ** @author: yangfeng
 ** @date: 2025/7/22 9:00
 ** @version: 1.0.0
 *********************************************************/
@Configuration
//开启方法级的安全控制
@EnableMethodSecurity
public class SecurityConfig {
    @Resource
    private SysUserDetailsService sysUserDetailsService;
    @Resource
    private JwtAuthFilter jwtAuthFilter;
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    //内存用户配置
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        //创建一个管理员账号
//        manager.createUser(User.builder().username("admin")
//                .password(passwordEncoder().encode("admin@123"))
////                .roles("ADMIN") //设置该用户具有的角色
//                .authorities("admin:save","admin:update","ROLE_ADMIN") //设置该用户具有的权限
//                .build()
//        );
//        //创建一个普通用户账号
//        manager.createUser(User.builder().username("zhangsan")
//                .password(passwordEncoder().encode("zs@123"))
//                .roles("user").authorities("query").build()
//        );
//        return manager;
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login",
                            "/error",
                            "/css/**",
                            "/js/**",
                            "/webjars/**",
                            "/favicon.ico",
                            "/.well-known/**").permitAll()// 放行登录页及静态资源
                    .requestMatchers("/auth/login").permitAll()
                    .anyRequest().authenticated() // 其他请求需认证
            )
            .userDetailsService(sysUserDetailsService)
//            .formLogin(Customizer.withDefaults())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable);
    return http.build();
}
}
