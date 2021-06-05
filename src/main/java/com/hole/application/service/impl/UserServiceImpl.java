package com.hole.application.service.impl;

import com.hole.application.service.UserService;
import com.hole.domain.entity.User;
import com.hole.domain.mapper.UserMapper;
import com.hole.infrastructure.utils.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    //登录
    public User login(String loginName, String password) {
        //调用根据用户名查询用户信息的方法
        User user = userMapper.findUserByLoginName(loginName);
        if(user!=null){
            //加密
            //String newPassword = PasswordUtil.md5(password, user.getLoginName(), 5);
            //比较密码是否一致
            //if(user.getPwd().equals(newPassword)){
            if(user.getPwd().equals(password)){
                return user;
            }
        }
        return null;
    }
}
