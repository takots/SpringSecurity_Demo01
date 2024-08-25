package com.bobwu.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
}
