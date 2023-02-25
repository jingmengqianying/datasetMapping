package com.example.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jixy
 * @date 2023/2/25
 */
@TableName("column_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tableName;

    private String name;

    private String type;

    private String label;

    private String createTime;

    private String updateTime;
}
