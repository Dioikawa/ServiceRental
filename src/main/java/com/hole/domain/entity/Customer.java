package com.hole.domain.entity;

import lombok.Data;

@Data
public class Customer {

    private Integer id;//客户id
    private String name;//客户名称
    private Integer typeId;//客户类型Id
    private String phone;//客户电话
    private String address;//客户联系地址
    private String mail;//客户邮箱
    //客户类型
    private String typeName;
}
