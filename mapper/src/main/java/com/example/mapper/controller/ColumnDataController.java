package com.example.mapper.controller;

import com.example.mapper.common.Result;
import com.example.mapper.dao.TableColumnDto;
import com.example.mapper.service.ColumnInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jixy
 * @date 2023/2/25
 */
@RestController
@RequestMapping("/columnManager")
public class ColumnDataController {
    private static final Logger logger = Logger.getLogger(ColumnDataController.class);

    @Autowired
    ColumnInfoService columnInfoService;

    @GetMapping("/getColumnInfoByTableName")
    public Result<?> findColumnByTableName(@RequestParam String tableName) {
        logger.info("start find column info");
        return Result.success(columnInfoService.findColumn(tableName));
    }

    @GetMapping("/getColumnInfoPage")
    public Result<?> findColumnPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String tableName) {
        logger.info("start find column info");
        return Result.success(columnInfoService.findColumnPage(pageNum, pageSize, tableName));
    }

    @PostMapping
    public Result<?> save(@RequestBody TableColumnDto columnInfo) {
        logger.info("start save");
        return Result.success(columnInfoService.saveInfo(columnInfo));
    }

    @PutMapping
    public Result<?> update(@RequestBody TableColumnDto tableColumnInfo) {
        logger.info("start update");
        return Result.success(columnInfoService.updateInfo(tableColumnInfo));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id) {
        return Result.success(columnInfoService.deleteById(id));
    }
}
