package com.heima.wemedia.feign;/**
 * @Description:
 */

import com.heima.apis.wemedia.IWemediaClient;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-06  19:35
 */
@RestController
public class WemediaClient implements IWemediaClient {
    @Autowired
    private WmChannelService wmChannelService;
    @Override
    @GetMapping("/api/v1/channel/channels")
    public ResponseResult getChannels() {
        return wmChannelService.findAll();
    }
}
