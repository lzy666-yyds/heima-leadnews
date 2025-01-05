package com.heima.behavior.controller.v1;/**
 * @Description:
 */

import com.heima.behavior.service.ApCollectionBehaviorService;
import com.heima.model.behavior.dtos.CollectionBehaviorDto;
import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-05  13:27
 */
@RestController
@RequestMapping("/api/v1/collection_behavior")
public class ApCollectionBehaviorController {
    @Autowired
    private ApCollectionBehaviorService apCollectionBehaviorService;

    @PostMapping
    public ResponseResult collectionBehavior(@RequestBody CollectionBehaviorDto dto) {
        return apCollectionBehaviorService.collectionBehavior(dto);
    }
}
