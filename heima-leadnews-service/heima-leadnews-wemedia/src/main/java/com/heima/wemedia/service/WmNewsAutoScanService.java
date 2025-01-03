package com.heima.wemedia.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmNews;

/**
 * @desription
 */
public interface WmNewsAutoScanService {
    /**
     * @Desc: 文章审核
     **/
    public void autoScanWmNews(Integer id);

    public ResponseResult saveAppArticle(WmNews wmNews);
}
