package com.heima.wemedia.service.impl;/**
 * @desription
 */


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-13  21:09
 */
@Service
@Transactional
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {

    @Autowired
    private WmChannelMapper wmChannelMapper;
    @Autowired
    private WmNewsMapper wmNewsMapper;
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }

    @Override
    public ResponseResult insert(WmChannel adChannel) {
        //根据名字先查询频道
        QueryWrapper<WmChannel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",adChannel.getName());
        WmChannel wmChannel = wmChannelMapper.selectOne(queryWrapper);
        if(wmChannel != null){
            return ResponseResult.errorResult(500,"频道已存在");
        }
        adChannel.setIsDefault(true);
        adChannel.setCreatedTime(new Date());
        wmChannelMapper.insert(adChannel);
        return ResponseResult.okResult(adChannel);
    }

    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        //- 查询需要按照创建时间倒序查询
        //- 按照频道名称模糊查询
        //- 可以按照状态进行精确查找（1：启用   true           0：禁用   false）
        //- 分页查询

        //检查参数
        dto.checkParam();
        Page page = new Page(dto.getPage(), dto.getSize());
        //分页查询
        LambdaQueryWrapper<WmChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(dto.getName() != null){
            lambdaQueryWrapper.like(WmChannel::getName,dto.getName());
        }
        page = page(page, lambdaQueryWrapper);
        //返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult delete(Integer id) {
        //只有禁用的频道才能删除
        WmChannel wmChannel = wmChannelMapper.selectById(id);
        if(!wmChannel.getStatus()) {
            wmChannelMapper.deleteById(id);
            return ResponseResult.okResult(200, "删除成功");
        }else{
            return ResponseResult.errorResult(500, "频道已启用，不能删除");
        }
    }

    @Override
    public ResponseResult updateChannel(WmChannel adChannel) {
        //- 点击编辑后可以修改频道
        //- 如果频道被引用则不能禁用
        if(adChannel.getStatus()){
            //禁用
            //查询该频道是否被引用
            LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(WmNews::getChannelId,adChannel.getId());
            int count = wmNewsMapper.selectCount(lambdaQueryWrapper);
            if(count > 0){
                return ResponseResult.errorResult(500,"该频道被引用，不能禁用");
            }
        }
        wmChannelMapper.updateById(adChannel);
        return ResponseResult.okResult(adChannel);
    }
}
