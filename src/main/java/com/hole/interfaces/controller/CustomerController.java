package com.hole.interfaces.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hole.application.service.CustomerService;
import com.hole.domain.entity.Customer;
import com.hole.domain.vo.CustomerVo;
import com.hole.infrastructure.utils.DataGridViewResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/addCustomer")
    public String addBill(Customer customer){
        Map<String,Object> map = new HashMap <String,Object>();
        if(customerService.addCustomer(customer)>0){
            map.put("success",true);
            map.put("message","添加成功");
        }else{
            map.put("success",false);
            map.put("message","添加失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/updateCustomer")
    public String updateBill(Customer customer){
        Map<String,Object> map = new HashMap<String,Object>();
        if(customerService.updateCustomer(customer)>0){
            map.put("success",true);
            map.put("message","修改成功");
        }else{
            map.put("success",false);
            map.put("message","修改失败");
        }
        return JSON.toJSONString(map);
    }
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        if(customerService.deleteById(id)>0){
            map.put("success",true);
            map.put("message","删除成功");
        }else{
            map.put("success",false);
            map.put("message","删除失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/batchDelete")
    public String batchDelete(String ids){
        Map<String,Object> map = new HashMap<String,Object>();
        int count = 0;
        //将字符串拆分成数组,原字符串用逗号分隔
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            count = customerService.deleteById(Integer.valueOf(idsStr[i]));
            if(count>0){
                map.put("success",true);
                map.put("message","删除成功");
            }
        }
        //判断受影响行数是否为0
        if(count<=0){
            map.put("success",false);
            map.put("message","删除失败");
        }
        return JSON.toJSONString(map);
    }
}
