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

    @Override
    public List<Customer> findCustomerList(CustomerVo customerVo) {
        return customerMapper.findCustomerList(customerVo);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    @Override
    public int deleteById(Integer id) {
        return customerMapper.deleteById(id);
    }
}
