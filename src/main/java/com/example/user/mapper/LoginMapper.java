package com.example.user.mapper;

import com.example.user.entity.Login;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginMapper {
    @Select("SELECT * FROM login WHERE id = #{id}")
    Login queryById(@Param("id") String id);

    @Select("SELECT * FROM login")
    List<Login> queryAll();

    @Select("SELECT id FROM login")
    List<String> queryId();

    @Insert({"INSERT INTO login(id,telephone,username,password) " +
            "VALUES(replace(uuid(), '-', ''),#{telephone},#{username},#{password})"})
    int add(Login login);

    @Delete("DELETE FROM login WHERE id = #{id}")
    int delById(String id);

    @Delete("DELETE FROM login WHERE telephone = #{telephone}")
    int delByTelephone(String teplephone);

    @Update("UPDATE user SET telephone=#{telephone},username=#{username},password=#{password} " +
            "WHERE id = #{id}")
    int updateById(Login login);
}
