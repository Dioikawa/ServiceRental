package com.hole.interfaces.controller;

import com.alibaba.fastjson.JSON;
import com.hole.domain.entity.User;
import com.hole.application.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        //创建map集合保存信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用登录的方法
        User loginUser = userService.login(loginName, password);
        //判断对象是否为空
        if(loginUser!=null){
            //保存会话
            session.setAttribute("loginUser",loginUser);
            map.put("success",true);//成功
        }else{
            map.put("success",false);//失败
        }
        //将Map集合的数据以JSON格式返回
        System.out.println(JSON.toJSONString(map));
        return JSON.toJSONString(map);
    }

}
