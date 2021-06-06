package com.hole.domain.mapper;

import com.hole.domain.entity.Type;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TypeMapper {

    //查询所有类型
    @Select("select * from type")
    List<Type> findTypeList();
}
