package com.heima.user.service.impl;/**
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.pojos.ApUserRealname;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:52
 */
@Service
@Slf4j
@Transactional
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    @Autowired
    private ApUserRealnameMapper apUserRealnameMapper;

    @Override
    public ResponseResult findAll(AuthDto dto) {
        // 校验参数
        if(dto==null){
            return ResponseResult.errorResult(500,"参数错误");
        }
        dto.checkParam();
        Page page = new Page(dto.getPage(), dto.getSize());
        // 分页查询
        LambdaQueryWrapper<ApUserRealname> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if( dto.getStatus()!=null){
            lambdaQueryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        page = page(page, lambdaQueryWrapper);
        // 返回结果
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        pageResponseResult.setData(page.getRecords());
        return pageResponseResult;
    }

    @Override
    public ResponseResult authFail(AuthDto dto) {
        if(dto==null || dto.getId()==null){
            return ResponseResult.errorResult(500,"参数错误");
        }
        ApUserRealname apUserRealname = apUserRealnameMapper.selectById(dto.getId());
        if(apUserRealname==null){
            return ResponseResult.errorResult(500,"用户不存在");
        }
        apUserRealname.setStatus((short) 2);
        apUserRealname.setReason(dto.getMsg());
        apUserRealnameMapper.updateById(apUserRealname);
        return ResponseResult.okResult(apUserRealname);
    }

    @Override
    public ResponseResult authPass(AuthDto dto) {
        if(dto==null || dto.getId()==null){
            return ResponseResult.errorResult(500,"参数错误");
        }
        ApUserRealname apUserRealname = apUserRealnameMapper.selectById(dto.getId());
        if(apUserRealname==null){
            return ResponseResult.errorResult(500,"用户不存在");
        }
        apUserRealname.setStatus((short) 9);
        apUserRealnameMapper.updateById(apUserRealname);
        return ResponseResult.okResult(apUserRealname);
    }
}
