package com.heima.behavior.service;

import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 */
public interface ApUnlikesBehaviorService {
    ResponseResult unlikesBehavior(UnLikesBehaviorDto dto);
}
