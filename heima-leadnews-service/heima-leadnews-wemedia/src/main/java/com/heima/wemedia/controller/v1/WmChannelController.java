package com.heima.wemedia.controller.v1;/**
 * @desription
 */

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-13  21:06
 */
@RestController
@RequestMapping("/api/v1/channel")
public class WmChannelController {
    @Autowired
    private WmChannelService wmChannelService;
    /**
     * @Desc:查询所有频道
     **/
    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }

    /**
     * @Desc:新增频道
     **/
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody WmChannel adChannel){
        return wmChannelService.insert(adChannel);
    }

    /**
     * @Desc:查询频道
     **/
    @PostMapping("/list")
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto){
        return wmChannelService.findByNameAndPage(dto);
    }
    /**
     * @Desc:删除频道
     **/
    @GetMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id){
        return wmChannelService.delete(id);
    }
    /**
     * @Desc: 修改频道
     **/
    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmChannel adChannel){
        return wmChannelService.updateChannel(adChannel);
    }
}
