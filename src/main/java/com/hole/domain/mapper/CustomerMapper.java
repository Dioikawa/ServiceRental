package com.hole.domain.mapper;


import com.hole.domain.entity.Customer;
import com.hole.domain.vo.CustomerVo;

import java.util.List;

public interface CustomerMapper {


    //查询客户列表，查询结果为多条记录，用List集合
    List<Customer> findCustomerList(CustomerVo customerVo);


    int addCustomer(Customer customer);


    int updateBill(Customer customer);


    int deleteById(Integer id);
}
