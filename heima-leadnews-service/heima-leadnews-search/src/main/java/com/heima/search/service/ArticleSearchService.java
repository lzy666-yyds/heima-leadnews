package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @Description:
 */
public interface ArticleSearchService {

    public ResponseResult search(UserSearchDto dto) throws IOException;
}