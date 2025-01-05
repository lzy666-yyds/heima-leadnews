package com.heima.model.article.dtos;/**
 * @Description:
 */

import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-05  13:51
 */
@Data
public class ArticleInfoDto {
    //| &emsp;articleId      | 文章id |      | false | long |      |
    //| -------------------- | ------ | ---- | ----- | ---- | ---- |
    //| &emsp;&emsp;authorId | 作者id |      | false | int  |      |
    private Long articleId;
    private Integer authorId;
}
