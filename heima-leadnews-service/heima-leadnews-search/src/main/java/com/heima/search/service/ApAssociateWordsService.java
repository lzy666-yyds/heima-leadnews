package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;

/**
 * @Description:
 */
public interface ApAssociateWordsService {
    ResponseResult search(UserSearchDto dto);
}
