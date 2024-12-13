package com.heima.model.wemedia.dtos;/**
 * @desription
 */

import com.heima.model.common.dtos.PageRequestDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-13  20:01
 */
@Data
public class WmMaterialDto extends PageRequestDto {
    //是否收藏 1 收藏 0 未收藏
    private Short isCollection;
}
