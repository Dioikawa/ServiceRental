package com.hole.application.service.impl;

import com.hole.application.service.TypeService;
import com.hole.domain.entity.Type;
import com.hole.domain.mapper.TypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    public List<Type> findTypeList() {
        return typeMapper.findTypeList();
    }
}
