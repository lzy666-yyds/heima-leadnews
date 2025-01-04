package com.heima.user.controller.v1;/**
 * @Description:
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.dtos.UserRelationDto;
import com.heima.user.service.ApUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  14:32
 */
@RestController
@RequestMapping("/api/v1/user")
public class ApUserRelationController {
    @Autowired
    private ApUserRelationService apUserRelationService;
    /**
     * @Desc:关注与取消关注
     **/
    @PostMapping("/user_follow")
    public ResponseResult follow(@RequestBody UserRelationDto dto) {
        return apUserRelationService.follow(dto);
    }
}
