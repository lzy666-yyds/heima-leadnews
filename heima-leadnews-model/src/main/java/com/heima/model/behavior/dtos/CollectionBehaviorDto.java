package com.heima.model.behavior.dtos;/**
 * @Description:
 */

import lombok.Data;

import java.util.Date;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-05  13:24
 */
@Data
public class CollectionBehaviorDto {
    //文章id
    private Long entryId;
    //文章类型 0文章 1动态
    private Short type;
    //操作类型 0收藏 1取消收藏
    private Short operation;
    //发布时间
    private Date publishedTime;
}
