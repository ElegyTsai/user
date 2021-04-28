package com.example.user.mapper;

import com.example.user.model.Login;
import com.example.user.model.LoginBase;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginMapper {
    @Select("SELECT * FROM login WHERE id = #{id}")
    LoginBase queryById(@Param("id") String id);

    @Select("SELECT * FROM login")
    List<LoginBase> queryAll();

    @Select("SELECT * FROM login WHERE telephone = #{telephone}")
    LoginBase queryByTelephone(@Param("telephone") String telephone);

    @Insert({"INSERT INTO login(id,telephone,username,password) " +
            "SELECT replace(uuid(), '-', ''),#{telephone},#{username},#{password} " +
            "WHERE NOT EXISTS (SELECT telephone FROM login WHERE telephone = #{telephone})"})
    int add(Login login);

    @Delete("DELETE FROM login WHERE id = #{id}")
    int delById(String id);

    @Delete("DELETE FROM login WHERE telephone = #{telephone}")
    int delByTelephone(String telephone);

    @Update("UPDATE login SET telephone=#{telephone},username=#{username},password=#{password} " +
            "WHERE id = #{id}")
    int updateById(Login login);

    @Update("UPDATE login SET username=#{username},password=#{password} " +
            "WHERE telephone = #{telephone}")
    int updateByTelephone(Login login);
}
