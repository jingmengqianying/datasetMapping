package com.example.mapper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mapper.entity.ManagerTable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/21
 */
public interface TableDataService extends IService<ManagerTable> {
    /**
     * 根据role查配置数据
     * @param role role
     * @return list
     */
    List<ManagerTable> findByRole(String role);

    /**
     * 根据SQL查分页数据
     * @param pageNum 当前页数
     * @param pageSize 每页数量
     * @param sql sql数据
     * @return page
     */
    IPage<?> findPageByNativeSql(int pageNum, int pageSize, String sql);
//    /**
//     * 获取配置信息
//     *
//     * @param role role
//     * @return List
//     */
//    List<ManagerTable> getMapperByRole(String role);
}
