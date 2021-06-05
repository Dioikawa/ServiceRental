package com.hole.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Project {
    private Integer id;//项目id
    private String name;//项目名称
    private Integer type;//项目类型
    private Integer customerId;//客户id
    private Date startTime;//开始时间
    private Date endTime;//结束时间

}
