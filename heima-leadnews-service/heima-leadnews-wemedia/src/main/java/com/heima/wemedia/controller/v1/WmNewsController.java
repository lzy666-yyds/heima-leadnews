package com.heima.wemedia.controller.v1;/**
 * @desription
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-14  16:17
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;
    /**
     * @Desc:查询自媒体文章
     **/
    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return wmNewsService.findAll(dto);
    }

    /**
     * @Desc:文章发布
     **/
    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return wmNewsService.submitNews(dto);
    }
}
