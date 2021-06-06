package com.hole.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //去到客户页面
    @RequestMapping("/customerPage")
    public String toCustomer(){
        return "customer";
    }

    //去到自由职业者页面
    @RequestMapping("/freelancerPage")
    public String toFreelancer(){
        return "freelancer";
    }

    //去到项目页面
    @RequestMapping("/projectPage")
    public String toProject(){
        return "project";
    }
}
