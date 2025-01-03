package com.heima.model.admin.dtos;/**
 * @Description:
 */

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:26
 */
@Data
public class SensitiveDto extends PageRequestDto {
    // 敏感词名称
    private String name;
}
