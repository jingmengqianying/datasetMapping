package com.example.mapper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.dao.TableColumnDto;
import com.example.mapper.entity.ColumnInfo;
import com.example.mapper.entity.TableInfo;
import com.example.mapper.mapper.ColumnMapper;
import com.example.mapper.service.ColumnInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/25
 */
@Service
public class ColumnInfoServiceImpl extends ServiceImpl<ColumnMapper, ColumnInfo> implements ColumnInfoService {
    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public List<ColumnInfo> findColumn(String tableName) {
        return columnMapper.findByTableName(tableName);
    }

    @Override
    public Page<ColumnInfo> findColumnPage(int pageNum, int pageSize, String tableName) {
        LambdaQueryWrapper<ColumnInfo> wrapper = Wrappers.lambdaQuery();
        if (!tableName.isEmpty()) {
            wrapper.eq(ColumnInfo::getTableName, tableName);
        }
        return columnMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }


    @Override
    public int saveInfo(TableColumnDto tableColumnDto) {
        ColumnInfo columnInfo = new ColumnInfo();
        BeanUtils.copyProperties(tableColumnDto, columnInfo);
        columnInfo.setCreateTime(String.valueOf(System.currentTimeMillis()));
        return columnMapper.insert(columnInfo);
    }

    @Override
    public int updateInfo(TableColumnDto tableColumnDto) {
        ColumnInfo columnInfo = new ColumnInfo();
        BeanUtils.copyProperties(tableColumnDto, columnInfo);
        return columnMapper.updateById(columnInfo);
    }

    @Override
    public int deleteById(int id) {
        return columnMapper.deleteById(id);
    }
}
