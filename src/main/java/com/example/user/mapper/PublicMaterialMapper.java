package com.example.user.mapper;

import com.example.user.model.PublicMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PublicMaterialMapper {
    @Select("SELECT * FROM public_material WHERE category = #{category}")
    List<PublicMaterial> queryByCate(@Param("category") String category);

    @Select("SELECT * FROM public_material WHERE pid = #{pid}")
    PublicMaterial queryByPid(@Param("pid") String pid);

    @Insert({"INSERT INTO public_material(pid,thumbnail_url,picture_url,category) " +
            "VALUES (#{pid},#{thumbnail_url},#{picture_url},#{category})"})
    int add(PublicMaterial publicMaterial);

    @Delete("DELETE FROM public_material WHERE pid = #{pid}")
    int delByPid(String pid);

}
