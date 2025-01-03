package com.heima.wemedia.service.impl;/**
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmSensitive;
import com.heima.wemedia.mapper.WmSensitiveMapper;
import com.heima.wemedia.service.WmSensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-03  12:28
 */
@Service
@Slf4j
@Transactional
public class WmSensitiveServiceImpl extends ServiceImpl<WmSensitiveMapper, WmSensitive> implements WmSensitiveService {
    @Override
    public ResponseResult findAll(SensitiveDto dto) {
        if(dto==null){
            return ResponseResult.errorResult(500,"参数错误");
        }
        dto.checkParam();
        Page page = new Page(dto.getPage(), dto.getSize());
        // 分页查询
        LambdaQueryWrapper<WmSensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (dto.getName() != null && dto.getName().length() > 0) {
            lambdaQueryWrapper.like(WmSensitive::getSensitives, dto.getName());
        }
        page = page(page, lambdaQueryWrapper);
        // 返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult insert(WmSensitive adSensitive) {
        // 判断参数
        if (adSensitive == null) {
            return ResponseResult.errorResult(500, "参数错误");
        }
        // 先判断是否已经存在
        if (adSensitive.getSensitives() != null && !adSensitive.getSensitives().isEmpty()) {
            WmSensitive sensitive = getOne(new LambdaQueryWrapper<WmSensitive>().eq(WmSensitive::getSensitives, adSensitive.getSensitives()));
            if (sensitive != null) {
                return ResponseResult.errorResult(500, "敏感词已存在");
            }
        }
        save(adSensitive);
        return ResponseResult.okResult(adSensitive);
    }

    @Override
    public ResponseResult delete(Integer id) {
        return removeById(id) ? ResponseResult.okResult(200, "删除成功") : ResponseResult.errorResult(500, "删除失败");
    }

    @Override
    public ResponseResult updateSensitive(WmSensitive adSensitive) {
        return updateById(adSensitive) ? ResponseResult.okResult(200, "修改成功") : ResponseResult.errorResult(500, "修改失败");
    }
}
