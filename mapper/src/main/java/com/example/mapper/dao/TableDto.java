package com.example.mapper.dao;

import lombok.Data;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Data
public class TableDto {
    private Integer id;

    private String tableName;

    private String tableDesc;

    private String role;

    private String createTime;
}
