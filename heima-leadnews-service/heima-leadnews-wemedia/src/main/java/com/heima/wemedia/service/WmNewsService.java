package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.NewsAuthDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

/**
 * @desription
 */
public interface WmNewsService extends IService<WmNews> {

    ResponseResult findAll(WmNewsPageReqDto dto);

    ResponseResult submitNews(WmNewsDto dto);


    ResponseResult downOrUp(WmNewsDto dto);

    ResponseResult listNews(NewsAuthDto dto);

    ResponseResult oneNews(Integer id);

    ResponseResult authFail(NewsAuthDto dto);

    ResponseResult authPass(NewsAuthDto dto);
}
