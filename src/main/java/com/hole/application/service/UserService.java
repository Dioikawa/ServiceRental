package com.hole.application.service;

import com.hole.domain.entity.User;

public interface UserService {
    User login(String loginName,String password);
}
