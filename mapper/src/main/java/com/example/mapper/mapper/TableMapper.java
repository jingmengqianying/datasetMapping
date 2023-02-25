package com.example.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mapper.dao.TableColumnDto;
import com.example.mapper.entity.TableInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Mapper
 * @author jixy
 * @date 2023/2/21
 */
@Repository
public interface TableMapper extends BaseMapper<TableInfo> {
    /**
     * 根据角色查询数据
     *
     * @param role role
     * @return List
     */
    @Select("select * from table_info where role=#{role}")
    List<TableInfo> findByRole(String role);

    /**
     * 查询当前数据库中的所有表
     * @return list
     */
    @Select("select table_name as name,table_comment as label from information_schema.tables where TABLE_SCHEMA=(select database())")
    List<Map<String,String>> findTableNameInDb();

    /**
     * 查询表的字段
     *
     * @param tableName t
     * @return list
     */
    @Select("select column_name as name, data_type as type, column_comment as label FROM " +
            "information_schema.COLUMNS WHERE TABLE_NAME IN (#{tableName}) " +
            "AND TABLE_SCHEMA=(select database())")
    List<TableColumnDto> findColumnInDb(String tableName);
}
