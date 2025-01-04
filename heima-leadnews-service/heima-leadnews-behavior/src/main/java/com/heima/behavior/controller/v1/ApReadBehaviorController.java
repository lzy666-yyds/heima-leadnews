package com.heima.behavior.controller.v1;/**
 * @Description:
 */

import com.heima.behavior.service.ApReadBehaviorService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  18:09
 */
@RestController
@RequestMapping("/api/v1/read_behavior")
public class ApReadBehaviorController {
    @Autowired
    private ApReadBehaviorService apReadBehaviorService;
    /**
     * @Desc:阅读
     **/
    @PostMapping
    public ResponseResult readBehavior(@RequestBody ReadBehaviorDto dto) {
        return apReadBehaviorService.readBehavior(dto);
    }
}
