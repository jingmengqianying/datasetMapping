package com.example.mapper.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jixy
 * @date 2023/2/21
 */
@Getter
@AllArgsConstructor
public enum FilterType {
    //等于
    EQUAL(1, "等于", "="),
    // 不等于
    NOT_EQUAL(2, "不等于", "!="),
    // 模糊查询
    LIKE(7, "模糊查询", "like");


    /**
     * 角色id
     */
    private final Integer typeId;

    /**
     * 描述
     */
    private final String name;

    /**
     * 标签
     */
    private final String label;
}
