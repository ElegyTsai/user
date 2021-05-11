package com.example.user.mapper;

import com.example.user.model.UserPicture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserPictureMapper {
    @Select("SELECT * FROM user_picture WHERE pid = #{pid}")
    UserPicture querybypid(@Param("pid") String pid);

    @Select("SELECT * FROM user_picture WHERE id = #{id}")
    List<UserPicture> queryById(@Param("id") String id);

    @Insert({"INSERT INTO user_picture(pid,id,thumbnail_url,file_url) " +
            "VALUES (replace(uuid(), '-', ''),#{id},#{thumbnail_url},#{file_url})"})
    int add(UserPicture userPicture);

    @Delete("DELETE FROM user_picture WHERE pid = #{pid}")
    int delByPid(String pid);
}
