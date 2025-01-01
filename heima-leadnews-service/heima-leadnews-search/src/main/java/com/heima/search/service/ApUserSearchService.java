package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.HistorySearchDto;

/**
 * @Description:
 */
public interface ApUserSearchService {
    /**
     * @Desc:保存用户搜索历史
     **/
    public void insert(String keyword,Integer userId);

    /**
     * @Desc: 加载搜索记录列表
     **/
    public ResponseResult findUserSearch();

    ResponseResult delUserSearch(HistorySearchDto dto);
}
