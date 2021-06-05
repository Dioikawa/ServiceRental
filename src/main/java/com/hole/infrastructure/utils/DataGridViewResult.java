package com.hole.infrastructure.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

//此类封装LayUI数据表格中返回的数据格式
@Data
@NoArgsConstructor
public class DataGridViewResult {
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;

    /**
     * 封装数据表格
     * @param count
     * @param data
     */
    public DataGridViewResult(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
