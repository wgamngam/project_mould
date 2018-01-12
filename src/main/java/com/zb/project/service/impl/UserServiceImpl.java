package com.zb.project.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zb.project.entity.User;
import com.zb.project.mapper.UserMapper;
import com.zb.project.service.UserService;

import java.util.List;

/**
 * Created by sen on 2016/8/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
