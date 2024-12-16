package com.heima.article.feign;/**
 * @desription
 */

import com.heima.apis.article.IArticleClient;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-16  15:35
 */
@RestController
public class ArticleClient implements IArticleClient {
    @Autowired
    private ApArticleService apArticleService;
    /**
     * @Desc:文章保存接口
     **/
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        return apArticleService.saveArticle(dto);
    }
}
