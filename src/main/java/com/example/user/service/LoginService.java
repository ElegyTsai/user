package com.example.user.service;

import com.example.user.model.Login;
import com.example.user.model.LoginBase;
import com.example.user.mapper.LoginMapper;
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

    public LoginBase queryByTelephone(String telephone) {
        return loginMapper.queryByTelephone(telephone);
    }

    public int add(Login login) {
        return loginMapper.add(login);
    }

    public int updateById(Login login) {
        return loginMapper.updateById(login);
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
