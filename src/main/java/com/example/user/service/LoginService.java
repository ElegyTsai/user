package com.example.user.service;

import com.example.user.model.Login;
import com.example.user.model.LoginBase;
import com.example.user.mapper.LoginMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;

    public LoginBase queryById(String id){
        return loginMapper.queryById(id);
    }

    public List<LoginBase> queryAll() {
        return loginMapper.queryAll();
    }

    public LoginBase queryByTelephone(String telephone, String password) {
        return loginMapper.queryByTelephone(telephone, password);
    }

    public LoginBase add(Login login) {
        String telephone = login.getTelephone();
        String password = login.getPassword();
        loginMapper.add(login);
        return loginMapper.queryByTelephone(telephone,password);
    }

    public LoginBase updateById(Login login) {
        String id = login.getId();
        loginMapper.updateById(login);
        return loginMapper.queryById(id);
    }

    public int updateByTelephone(Login login) {
        return loginMapper.updateByTelephone(login);
    }

    public int delById(String id) {
        return loginMapper.delById(id);
    }

    public int delByTelephone(String telephone) {
        return loginMapper.delByTelephone(telephone);
    }

}
