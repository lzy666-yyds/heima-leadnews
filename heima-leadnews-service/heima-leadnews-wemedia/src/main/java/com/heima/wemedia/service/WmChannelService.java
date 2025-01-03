package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.wemedia.mapper.WmChannelMapper;

/**
 * @desription
 */
public interface WmChannelService extends IService<WmChannel> {
    ResponseResult findAll();

    ResponseResult insert(WmChannel adChannel);

    ResponseResult findByNameAndPage(ChannelDto dto);

    ResponseResult delete(Integer id);

    ResponseResult updateChannel(WmChannel adChannel);
}
