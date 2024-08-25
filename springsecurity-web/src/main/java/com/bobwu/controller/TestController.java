package com.bobwu.controller;

import com.bobwu.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("hello")
    public String hello(){
        return "Hello! Security";
    }

    @GetMapping("index")
    public String index(){
        return "Hello! index";
    }

    @GetMapping("update")
//    @Secured({"ROLE_normal","ROLE_sale"})
//    @PreAuthorize("hasAnyAuthority('admins')") //進入方法前先驗證 //hasAuthority,hasAnyAuthority,hasRole,hasAnyRole
//    @PreAuthorize("hasAnyRole('ROLE_normal', 'ROLE_sale')")
    @PostAuthorize("hasAnyAuthority('admins')") //方法後進行驗證
    public String update(){
        System.out.println("update ....");
        return "Hello! update";
    }

    @GetMapping("getAll")
    @PostAuthorize("hasAnyAuthority('admins')")//方法後進行驗證
    @PostFilter("filterObject.username == 'admin1'") //返回的用户列表中，只有 username 为 'admin1' 的用户会被保留。
    public List<Users> getAllUser() {
        List<Users> list = new ArrayList<>();
        list.add(new Users(1, "admin1", "6666"));
        list.add(new Users(2, "admin2", "88888"));
        return list;
    }


}