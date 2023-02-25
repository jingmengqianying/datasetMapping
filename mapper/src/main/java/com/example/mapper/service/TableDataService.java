package com.example.mapper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mapper.dao.TableDto;
import com.example.mapper.entity.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * @author jixy
 * @date 2023/2/21
 */
public interface TableDataService extends IService<TableInfo> {
    /**
     * 根据role查配置数据
     * @param role role
     * @return list
     */
    List<TableInfo> findByRole(String role);

    /**
     * 根据SQL查分页数据
     * @param pageNum 当前页数
     * @param pageSize 每页数量
     * @param sql sql数据
     * @return page
     */
    IPage<?> findPageByNativeSql(int pageNum, int pageSize, String sql);

    /**
     * 查找数据库中的所有表
     * @return list
     */
    List<Map<String,String>> findTableNameInDb();

    /**
     * 根据表信息查数据库中的字段信息
     * @param tableName table
     * @return list
     */
    List<?> findColumnInDb(String tableName);

    /**
     * 保存
     * @param tableDto tableInfo
     * @return int
     */
    int saveInfo(TableDto tableDto);

    /**
     * 更新
     * @param tableDto tableInfo
     * @return int
     */
    int updateInfo(TableDto tableDto);
}
