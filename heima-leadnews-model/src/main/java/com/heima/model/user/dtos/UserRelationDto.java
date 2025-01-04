package com.heima.model.user.dtos;/**
 * @Description:
 */

import com.heima.model.common.annotation.IdEncrypt;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  14:21
 */
@Data
public class UserRelationDto {
    //文章作者ID
    @IdEncrypt
    private Integer authorId;
    //文章ID
    @IdEncrypt
    private Long articleId;
    /**
     * @Desc:操作方式 0 关注 1 取消关注
     **/
    private Short operation;
}
