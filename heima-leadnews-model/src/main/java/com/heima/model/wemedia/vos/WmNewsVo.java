package com.heima.model.wemedia.vos;/**
 * @Description:
 */

import com.heima.model.wemedia.pojos.WmNews;
import lombok.Data;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  13:44
 */
@Data
public class WmNewsVo extends WmNews {
    //作者名称
    private String authorName;
}
