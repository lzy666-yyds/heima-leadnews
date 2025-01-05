package com.heima.model.behavior.dtos;/**
 * @Description:
 */

import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-05  13:10
 */
@Data
public class UnLikesBehaviorDto {
    private Long articleId;
    //0 不喜欢  1 取消不喜欢
    private Short type;
}
