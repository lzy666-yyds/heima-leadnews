package com.heima.wemedia.controller.v1;/**
 * @desription
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-13  18:12
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {
    @Autowired
    private WmMaterialService wmMaterialService;
    /**
     * @Desc:上传图片
     **/
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile){
        return wmMaterialService.uploadPicture(multipartFile);
    }

    /**
     * @Desc:素材列表查询
     **/
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto){
        return wmMaterialService.findList(dto);
    }
}
