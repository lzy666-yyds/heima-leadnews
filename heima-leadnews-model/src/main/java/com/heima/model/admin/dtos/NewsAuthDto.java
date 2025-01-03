package com.heima.model.admin.dtos;/**
 * @Description:
 */

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  13:27
 */
@Data
public class NewsAuthDto extends PageRequestDto {
    // 文章id
    private Integer id;
    // 审核信息
    private String msg;
    // 审核状态
    private Integer status;
    // 标题
    private String title;
}
