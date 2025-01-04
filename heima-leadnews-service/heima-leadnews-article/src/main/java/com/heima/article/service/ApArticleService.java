package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mess.ArticleVisitStreamMess;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * @desription
 */
public interface ApArticleService extends IService<ApArticle> {
    /**
     * @Desc:根据参数加载文章列表
     **/
    ResponseResult load(Short loadtype, ArticleHomeDto dto);

    /**
     * @Desc:保存文章
     **/
    ResponseResult saveArticle(ArticleDto dto);

    void updateScore(ArticleVisitStreamMess mess);
}
