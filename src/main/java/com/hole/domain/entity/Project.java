package com.hole.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Project {
    private Integer id;//项目id
    private String name;//项目名称
    private Integer typeId;//项目类型id
    private Integer customerId;//客户id
    private Integer freelancerId;//自由职业者id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;//开始时间
    private Date endDate;//结束时间

}
