package com.example.user.controller;

import com.example.user.model.Login;
import com.example.user.model.LoginBase;
import com.example.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/querybyid")
    @ResponseBody
    public LoginBase queryById(@RequestBody String id){
        return loginService.queryById(id);
    }

    @RequestMapping("/")
    public List<LoginBase> queryAll() {
        return loginService.queryAll();
    }

    @RequestMapping("/querybytele")
    public LoginBase queryByTelephone(String telephone, String password) {
        return loginService.queryByTelephone(telephone, password);
    }

    @RequestMapping("/add")
    public LoginBase add(Login login) {
        return loginService.add(login);
    }

    @RequestMapping("/updatebyid")
    public LoginBase updateById(Login login) {
        return loginService.updateById(login);
    }

    @RequestMapping("/updatebytele")
    public String updateByTelephone(Login login) {
        return loginService.updateByTelephone(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbyid")
    public String delById(String id) {
        return loginService.delById(id) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbytele")
    public String delByTelephone(String telephone) {
        return loginService.delByTelephone(telephone) == 1 ? "success" : "failed";
    }
}
