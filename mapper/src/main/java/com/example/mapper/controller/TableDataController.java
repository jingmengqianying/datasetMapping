package com.example.mapper.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mapper.common.Result;
import com.example.mapper.dao.DataDto;
import com.example.mapper.service.TableDataService;
import com.example.mapper.util.SqlUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前台能查询到的数据
 *
 * @author jixy
 * @date 2023/2/21
 */
@RestController
@RequestMapping("/manager")
public class TableDataController {
    private static final Logger logger = Logger.getLogger(TableDataController.class);

    @Autowired
    TableDataService tableDataService;

    @GetMapping("/getByRole")
    public Result<?> findByRole(@RequestParam String role) {
        logger.info("start find Role");
        return Result.success(tableDataService.findByRole(role));
    }

    @PostMapping("/getBySql")
    public Result<?> findData(@RequestParam Integer pageNum,@RequestParam Integer pageSize, @RequestBody DataDto data) {
        logger.info("start find data");
        String sql = SqlUtil.getSql(data);
        IPage<?> page = tableDataService.findPageByNativeSql(pageNum-1, pageSize, sql);
        return Result.success(page);
    }
}
