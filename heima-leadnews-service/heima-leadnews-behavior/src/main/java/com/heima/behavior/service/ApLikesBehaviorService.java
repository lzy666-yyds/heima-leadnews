package com.heima.behavior.service;

import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 */
public interface ApLikesBehaviorService {
    ResponseResult likesBehavior(LikesBehaviorDto dto);
}
