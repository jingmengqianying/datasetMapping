package com.example.mapper.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.example.mapper.dao.TableDto;
import com.example.mapper.entity.TableInfo;
import com.example.mapper.mapper.TableMapper;
import com.example.mapper.service.TableDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Service
public class TableDataServiceImpl extends ServiceImpl<TableMapper, TableInfo> implements TableDataService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<TableInfo> findByRole(String role) {
        return tableMapper.findByRole(role);
    }

    @Override
    public IPage<?> findPageByNativeSql(int pageNum, int pageSize, String sql) {
        return SqlRunner.db().selectPage(new Page<>(pageNum, pageSize), sql);
    }

    @Override
    public List<Map<String,String>> findTableNameInDb() {
        return tableMapper.findTableNameInDb();
    }

    @Override
    public List<?> findColumnInDb(String tableName) {
        return tableMapper.findColumnInDb(tableName);
    }

    @Override
    public int saveInfo(TableDto tableDto) {
        TableInfo tableInfo = new TableInfo();
        BeanUtils.copyProperties(tableDto, tableInfo);
        tableInfo.setRole("admin");
        tableInfo.setCreateTime(String.valueOf(System.currentTimeMillis()));
        return tableMapper.insert(tableInfo);
    }

    @Override
    public int updateInfo(TableDto tableDto) {
        TableInfo tableInfo = new TableInfo();
        BeanUtils.copyProperties(tableDto, tableInfo);
        return tableMapper.updateById(tableInfo);
    }
}
