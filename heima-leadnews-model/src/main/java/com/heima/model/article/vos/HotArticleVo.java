package com.heima.model.article.vos;/**
 * @Description:
 */

import com.heima.model.article.pojos.ApArticle;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  16:44
 */
@Data
public class HotArticleVo extends ApArticle {
    /**
     * @Desc: 文章分数
     **/
    private Integer score;
}
