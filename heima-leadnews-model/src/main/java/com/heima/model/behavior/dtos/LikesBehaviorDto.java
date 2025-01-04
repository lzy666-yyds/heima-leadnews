package com.heima.model.behavior.dtos;/**
 * @Description:
 */

import com.heima.model.common.annotation.IdEncrypt;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  15:31
 */
@Data
public class LikesBehaviorDto {
    //文章id
    @IdEncrypt
    private Integer articleId;
    //操作类型 0 点赞 1 取消点赞
    private Short operation;
    //类型 0文章 1动态 2评论
    private Short type;
}
