package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 */
public interface ApReadBehaviorService {
    ResponseResult readBehavior(ReadBehaviorDto dto);
}
