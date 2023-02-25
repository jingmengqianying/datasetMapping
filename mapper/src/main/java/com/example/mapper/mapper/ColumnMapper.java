package com.example.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.entity.ColumnInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/25
 */
@Repository
public interface ColumnMapper extends BaseMapper<ColumnInfo> {
    /**
     * 根据表查询列信息
     *
     * @param name name
     * @return List
     */
    @Select("select * from column_info where table_name=#{name}")
    List<ColumnInfo> findByTableName(String name);
}
