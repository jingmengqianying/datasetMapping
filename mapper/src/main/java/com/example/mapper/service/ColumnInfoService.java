package com.example.mapper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mapper.dao.TableColumnDto;
import com.example.mapper.entity.ColumnInfo;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/25
 */
public interface ColumnInfoService extends IService<ColumnInfo> {

    /**
     * 根据role查配置数据
     * @param tableName tableName
     * @return list
     */
    List<ColumnInfo> findColumn(String tableName);

    /**
     * 根据列名查列配置信息
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param tableName tableName
     * @return page
     */
    Page<ColumnInfo> findColumnPage(int pageNum, int pageSize, String tableName);

    /**
     * 根据表信息查数据库中的字段信息
     * @param tableDto tableInfo
     * @return int
     */
    int saveInfo(TableColumnDto tableDto);

    /**
     * 根据表信息查数据库中的字段信息
     * @param tableDto tableInfo
     * @return int
     */
    int updateInfo(TableColumnDto tableDto);


    /**
     * 根据表信息查数据库中的字段信息
     * @param id id
     * @return int
     */
    int deleteById(int id);
}
