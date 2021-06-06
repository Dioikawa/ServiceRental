package com.hole.application.service.impl;

import com.hole.application.service.CustomerService;
import com.hole.domain.entity.Customer;
import com.hole.domain.mapper.CustomerMapper;
import com.hole.domain.vo.CustomerVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    public List<Customer> findCustomerList(CustomerVo customerVo) {
        return customerMapper.findCustomerList(customerVo);
    }
}
