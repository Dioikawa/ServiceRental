package com.hole.domain.vo;

import lombok.Data;


//自定义客户查询条件类
@Data
public class CustomerVo{

    //注意：page和limit属性要与LayUI的数据表格参数一致
    private Integer page;//当前页码
    private Integer limit;//每页显示数量

    private Integer typeId;
    private String name;
    private Integer id;
}
