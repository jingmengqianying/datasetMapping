package com.example.mapper.dao;

import lombok.Data;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Data
public class TableColumnDto {
    private String tableName;

    private String name;

    private String label;

    private String type;
}
