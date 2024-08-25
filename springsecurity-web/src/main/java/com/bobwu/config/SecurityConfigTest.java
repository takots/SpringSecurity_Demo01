package com.bobwu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //        退出
        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/test/hello").permitAll();


//        配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()
                .loginPage("/login.html")  // 自定义登录页面路径
                .loginProcessingUrl("/user/login") // 登陸訪問路徑
                .defaultSuccessUrl("/success.html").permitAll() // 登录成功后的跳转路径 . // 允许所有人访问登录页面
                .and().authorizeRequests()
                .antMatchers("/","/test/hello","/user/login").permitAll() // 哪些路徑可以直接訪問不需要驗證
                //                    当前登录用户，只有具备admin权限才可以访问这个路径
//                    .antMatchers("/test/index").hasAuthority("admins")
                // 具備其中一個角色
//                    .antMatchers("/test/index").hasAnyAuthority("admins,manager")
                // ROLE_
                .antMatchers("/test/index").hasRole("sale")
                .anyRequest().authenticated() //所有請求都可以訪問
                .and().csrf().disable(); //關閉csrf防護
    }
}