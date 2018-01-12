package com.zb.project.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zb.project.entity.User;
import com.zb.project.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction{

    private final static Logger logger = LoggerFactory.getLogger(UserAction.class);


        @Autowired
        UserService userService;

        @RequestMapping("/query")
        @ResponseBody
        public List<User> queryUser(){
           return userService.queryUser();
        }


}
