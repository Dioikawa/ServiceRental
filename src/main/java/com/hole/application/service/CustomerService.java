package com.hole.application.service;

import com.hole.domain.entity.Customer;
import com.hole.domain.vo.CustomerVo;

import java.util.List;

public interface CustomerService {
    List<Customer> findCustomerList(CustomerVo customerVo);

    int addCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteById(Integer id);
}
