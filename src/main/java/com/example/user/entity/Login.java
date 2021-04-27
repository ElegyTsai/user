package com.example.user.entity;

import lombok.Data;

@Data
public class Login extends LoginBase{
    private String telephone;
    private String password;
}
