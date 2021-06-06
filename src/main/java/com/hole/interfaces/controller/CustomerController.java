package com.hole.interfaces.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hole.application.service.CustomerService;
import com.hole.domain.entity.Customer;
import com.hole.domain.vo.CustomerVo;
import com.hole.infrastructure.utils.DataGridViewResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/list")
    public DataGridViewResult list(CustomerVo customerVo) {
        //设置分页信息(当前页码,每页显示数量)
        PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        //调用分页查询账单列表的方法
        List<Customer> customerList = customerService.findCustomerList(customerVo);
        //创建分页对象
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(customerList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
