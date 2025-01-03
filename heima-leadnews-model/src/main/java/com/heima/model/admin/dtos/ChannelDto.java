package com.heima.model.admin.dtos;/**
 * @Description:
 */

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  10:36
 */
@Data
public class ChannelDto extends PageRequestDto {
    //频道名称
    private String name;
}
