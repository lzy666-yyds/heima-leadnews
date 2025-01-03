package com.heima.admin.controller.v1;/**
 * @Description:
 */

import com.heima.admin.service.AdUserService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-02  19:24
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AdUserService adUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUserDto dto){
        return adUserService.login(dto);
    }
}
