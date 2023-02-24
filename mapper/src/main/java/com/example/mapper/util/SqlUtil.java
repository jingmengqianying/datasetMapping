package com.example.mapper.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.mapper.dao.ConditionInfo;
import com.example.mapper.dao.DataDto;
import com.example.mapper.enums.FilterType;

import java.util.List;

/**
 * @author jixy
 * @date 2023/2/23
 */
public class SqlUtil {

    public static String getSql(DataDto queryTableInfo) {
        return "select " + getColumns(queryTableInfo.getColumnList()) + " from " +
                queryTableInfo.getTableName() + generateWhereCond(queryTableInfo);
    }

    private static String getColumns(List<String> columns) {
        return CollectionUtils.isEmpty(columns) ? "*" : String.join(",", columns);
    }

    private static String generateWhereCond(DataDto queryTableInfo) {
        List<ConditionInfo> conditions = queryTableInfo.getConditions();
        if (CollectionUtils.isEmpty(conditions)) {
            return "";
        }
        StringBuilder whereCondition = new StringBuilder(" where ");
        for (ConditionInfo conditionInfo : conditions) {
            whereCondition.append(conditionInfo.getName())
                    .append(getConditionByType(conditionInfo)).append(" and ");
        }
        //去除最后的add
        return whereCondition.substring(0, whereCondition.length() - 5);
    }

    private static String getConditionByType(ConditionInfo conditionInfo) {
        String type = conditionInfo.getType();
        if (FilterType.LIKE.getLabel().equals(type)) {
            return " like %" + conditionInfo.getValue() + "%";
        }
        return type + " " + conditionInfo.getValue();
    }

}
