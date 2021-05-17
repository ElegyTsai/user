package com.example.user.model;

import lombok.Data;

@Data
public class Login extends LoginBase{
    private String telephone;
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }
}
