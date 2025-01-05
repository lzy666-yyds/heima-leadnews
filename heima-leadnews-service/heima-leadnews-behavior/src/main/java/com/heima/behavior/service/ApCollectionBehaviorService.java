package com.heima.behavior.service;

import com.heima.model.behavior.dtos.CollectionBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 */
public interface ApCollectionBehaviorService {
    ResponseResult collectionBehavior(CollectionBehaviorDto dto);
}
