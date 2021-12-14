package com.example.xyshw.mapper;

import com.example.xyshw.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("Insert into User (userName,passWord,sex,nickName,introduction,contact,email,campus,gmtCreate,gmtModified) values (#{userName}, #{passWord}, #{sex}, #{nickName},#{introduction},#{contact},#{email}, #{campus},#{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select("select id from User where userName=#{userName}")
    List<Integer> selectIdByUserName(@Param("userName") String userName);
}
