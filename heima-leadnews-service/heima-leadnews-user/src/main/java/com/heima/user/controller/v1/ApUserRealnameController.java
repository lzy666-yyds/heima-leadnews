package com.heima.user.controller.v1;/**
 * @Description:
 */

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:49
 */
@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController {
    @Autowired
    private ApUserRealnameService apUserRealnameService;
    /**
     * @Desc:查询用户审核列表
     **/
    @PostMapping("/list")
    public ResponseResult list(@RequestBody AuthDto dto) {
        return apUserRealnameService.findAll(dto);
    }
    /**
     * @Desc:审核失败
     **/
    @PostMapping("/authFail")
    public ResponseResult authFail(@RequestBody AuthDto dto) {
        return apUserRealnameService.authFail(dto);
    }
    /**
     * @Desc: 审核成功
     **/
    @PostMapping("/authPass")
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        return apUserRealnameService.authPass(dto);
    }
}
