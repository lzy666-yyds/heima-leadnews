package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmSensitive;

/**
 * @Description:
 */
public interface WmSensitiveService extends IService<WmSensitive> {
    ResponseResult findAll(SensitiveDto dto);

    ResponseResult insert(WmSensitive adSensitive);

    ResponseResult delete(Integer id);

    ResponseResult updateSensitive(WmSensitive adSensitive);
}
