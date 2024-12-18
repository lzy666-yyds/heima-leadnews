package com.heima.article.service;

import com.heima.model.article.pojos.ApArticle;

/**
 * @desription :文章静态文件生成并上传到minIO
 */
public interface ArticleFreemarkerService {
    /**
     * @Desc: 生成静态文件上传到minIO中
     **/
    public void buildArticleToMinIO(ApArticle apArticle, String content);
}
