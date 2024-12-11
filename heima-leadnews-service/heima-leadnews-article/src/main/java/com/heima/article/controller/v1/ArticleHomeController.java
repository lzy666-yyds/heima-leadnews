package com.heima.article.controller.v1;/**
 * @desription
 */

import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:首页
 *@Author: lzy
 *@CreateTime: 2024-12-11  16:03
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Autowired
    private ApArticleService apArticleService;
    /**
     * @Desc:加载首页
     **/
    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_MORE,dto);
    }

    /**
     * @Desc:加载更多
     **/
    @PostMapping("/loadmore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_MORE,dto);
    }

    /**
     * @Desc:加载最新
     **/
    @PostMapping("/loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto){
        return apArticleService.load(ArticleConstants.LOADTYPE_LOAD_NEW,dto);
    }
}
