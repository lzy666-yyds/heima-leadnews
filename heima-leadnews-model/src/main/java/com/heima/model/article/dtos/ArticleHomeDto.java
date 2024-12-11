package com.heima.model.article.dtos;/**
 * @desription
 */

import lombok.Data;

import java.util.Date;

/**
 *@Description:前端传过来的首页信息
 *@Author: lzy
 *@CreateTime: 2024-12-11  15:57
 */
@Data
public class ArticleHomeDto {

    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;
}
