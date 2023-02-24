package com.example.mapper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.example.mapper.entity.ManagerTable;
import com.example.mapper.mapper.TableMapper;
import com.example.mapper.service.TableDataService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Service
public class TableDataServiceImpl extends ServiceImpl<TableMapper, ManagerTable> implements TableDataService {

    @Autowired
    private TableMapper tableMapper;

    //    @Override
//    public Page<?> getNativeSql(String sql) {
//        Map<String, Object> map = CollectionUtils.newHashMapWithExpectedSize(2);
//        map.put(Constants.WRAPPER, queryWrapper);
//        map.put("page", page);
//        SqlSession sqlSession = sqlSession();
//        try {
//            page.setRecords(sqlSession.selectList(sqlStatement(SqlMethod.SELECT_PAGE), map));
//        } finally {
//            closeSqlSession(sqlSession);
//        }
//        return page;
//    }

    @Override
    public List<ManagerTable> findByRole(String role) {
        return tableMapper.findByRole(role);
    }

    @Override
    public IPage<?> findPageByNativeSql(int pageNum, int pageSize, String sql) {
        return SqlRunner.db().selectPage(new Page<>(pageNum, pageSize), sql);
    }

//
//    @Override
//    public List<ManagerTable> getMapperByRole(String role) {
//        return  }

}
