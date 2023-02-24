package com.example.mapper.dao;

import lombok.Data;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/23
 */
@Data
public class DataDto {
    private String tableName;
    private List<String> columnList;
    private List<ConditionInfo> conditions;
}
