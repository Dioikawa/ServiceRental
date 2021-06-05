package com.hole.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //去到客户页面
    @RequestMapping("/customer")
    public String toCustomer(){
        return "customer";
    }

    @RequestMapping("/freelancer")
    public String toFreelancer(){
        return "freelancer";
    }

    @RequestMapping("/project")
    public String toProject(){
        return "project";
    }
}
