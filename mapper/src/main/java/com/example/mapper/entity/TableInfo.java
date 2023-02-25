package com.example.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jixy
 * @date 2023/2/21
 */
@TableName("table_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tableName;

    private String tableDesc;

    private String role;

    private String createTime;

    private String updateTime;
}
