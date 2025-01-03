package com.heima.wemedia.controller.v1;/**
 * @desription
 */

import com.heima.model.admin.dtos.NewsAuthDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * @Desc:文章上下架功能
     **/
    @PostMapping("/down_or_up")
    public ResponseResult downOrUp(@RequestBody WmNewsDto dto){
        return wmNewsService.downOrUp(dto);
    }
    /**
     * @Desc:查询文章列表
     **/
    @PostMapping("/list_vo")
    public ResponseResult listNews(@RequestBody NewsAuthDto dto){
        return wmNewsService.listNews(dto);
    }
    /**
     * @Desc:查询文章详情
     **/
    @GetMapping("/one_vo/{id}")
    public ResponseResult oneNews(@PathVariable Integer id){
        return wmNewsService.oneNews(id);
    }
    /**
     * @Desc:文章审核失败
     **/
    @PostMapping("/auth_fail")
    public ResponseResult authFail(@RequestBody NewsAuthDto dto){
        return wmNewsService.authFail(dto);
    }
    /**
     * @Desc: 文章审核通过
     **/
    @PostMapping("/auth_pass")
    public ResponseResult authPass(@RequestBody NewsAuthDto dto){
        return wmNewsService.authPass(dto);
    }
}
