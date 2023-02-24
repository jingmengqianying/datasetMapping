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
 * @date 2023/2/21
 */
@TableName("mapper")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerTable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tableName;

    private String tableDesc;

    private String info;

    private String role;

    private String createTime;
}
