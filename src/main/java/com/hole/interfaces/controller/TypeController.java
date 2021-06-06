package com.hole.interfaces.controller;


import com.hole.application.service.TypeService;
import com.hole.domain.entity.Type;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;


    //查询类型
    @RequestMapping("list")
    public List<Type> list(){
        return typeService.findTypeList();
    }
}
