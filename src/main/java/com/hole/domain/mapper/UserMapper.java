package com.hole.domain.mapper;

import com.hole.domain.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    //根据登录名称查询用户信息
    @Select("select * from user where loginName = #{loginName}")
    User findUserByLoginName(String loginName);
}
