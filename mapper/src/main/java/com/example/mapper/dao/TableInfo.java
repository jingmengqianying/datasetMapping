package com.example.mapper.dao;

import lombok.Data;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Data
public class TableInfo {
    private String name;
    private String desc;
    private List<TableColumnInfo> columns;
}
