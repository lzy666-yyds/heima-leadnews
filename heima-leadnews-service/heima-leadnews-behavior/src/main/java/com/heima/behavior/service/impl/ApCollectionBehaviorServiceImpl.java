package com.heima.behavior.service.impl;/**
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.heima.behavior.service.ApCollectionBehaviorService;
import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.behavior.dtos.CollectionBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-05  13:28
 */
@Service
@Slf4j
@Transactional
public class ApCollectionBehaviorServiceImpl implements ApCollectionBehaviorService {
    @Autowired
    private CacheService cacheService;
    @Override
    public ResponseResult collectionBehavior(CollectionBehaviorDto dto) {
        //1.参数校验
        if(dto==null||dto.getEntryId()==null||dto.getOperation()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //3.查询收藏记录
        Object get = cacheService.hGet(BehaviorConstants.COLLECTION_BEHAVIOR + user.getId(), dto.getEntryId().toString());
        if(get!=null&&dto.getOperation()==0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"已收藏");
        }
        //4.根据操作添加或删除收藏
        if(dto.getOperation()==0){
            cacheService.hPut(BehaviorConstants.COLLECTION_BEHAVIOR+user.getId(), dto.getEntryId().toString(), JSON.toJSONString(dto));
        }else if(dto.getOperation()==1){
            cacheService.hDelete(BehaviorConstants.COLLECTION_BEHAVIOR+user.getId(), dto.getEntryId().toString());
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
