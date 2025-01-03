package com.heima.model.user.dtos;/**
 * @Description:
 */

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:50
 */
@Data
public class AuthDto extends PageRequestDto {

    private Integer id;

    private String msg;

    private Integer status;
}
