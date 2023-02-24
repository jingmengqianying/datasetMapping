package com.example.mapper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.entity.ManagerTable;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper
 * @author jixy
 * @date 2023/2/21
 */
@Repository
public interface TableMapper extends BaseMapper<ManagerTable> {
    /**
     * 根据角色查询数据
     *
     * @param role role
     * @return List
     */
    @Select("select * from mapper where role=#{role}")
    List<ManagerTable> findByRole(String role);

}
