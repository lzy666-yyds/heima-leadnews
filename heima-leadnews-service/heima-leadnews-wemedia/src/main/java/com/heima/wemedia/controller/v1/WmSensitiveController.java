package com.heima.wemedia.controller.v1;/**
 * @Description:
 */

import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmSensitive;
import com.heima.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:24
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {
    @Autowired
    private WmSensitiveService wmSensitiveService;
    /**
     * @Desc:查询敏感词列表
     **/
    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody SensitiveDto dto) {
        return wmSensitiveService.findAll(dto);
    }

    /**
     * @Desc: 新增敏感词
     **/
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody WmSensitive adSensitive) {
        return wmSensitiveService.insert(adSensitive);
    }
    /**
     * @Desc: 删除敏感词
     **/
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable Integer id) {
        return wmSensitiveService.delete(id);
    }
    /**
     * @Desc: 修改敏感词
     **/
    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmSensitive adSensitive) {
        return wmSensitiveService.updateSensitive(adSensitive);
    }
}
