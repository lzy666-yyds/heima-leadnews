package com.heima.behavior.controller.v1;/**
 * @Description:
 */

import com.heima.behavior.service.ApLikesBehaviorService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  15:53
 */
@RestController
@RequestMapping("/api/v1/likes_behavior")
public class ApLikesBehaviorController {
    @Autowired
    private ApLikesBehaviorService apLikesBehaviorService;
    /**
     * @Desc:点赞
     **/
    public ResponseResult likesBehavior(@RequestBody LikesBehaviorDto dto) {
        return apLikesBehaviorService.likesBehavior(dto);
    }
}
