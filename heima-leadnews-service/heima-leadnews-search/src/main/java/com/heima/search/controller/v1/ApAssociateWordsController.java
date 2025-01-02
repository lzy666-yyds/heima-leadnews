package com.heima.search.controller.v1;/**
 * @Description:
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;
import com.heima.search.service.ApAssociateWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-02  14:06
 */
@RestController
@RequestMapping("/api/v1/associate")
public class ApAssociateWordsController {
    @Autowired
    private ApAssociateWordsService apAssociateWordsService;
    /**
     * @Desc:关键字联想词
     **/
    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto dto) {
        return apAssociateWordsService.search(dto);
    }
}
