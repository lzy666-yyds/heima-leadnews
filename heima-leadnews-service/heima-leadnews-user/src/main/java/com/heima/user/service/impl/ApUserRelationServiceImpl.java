package com.heima.user.service.impl;/**
 * @Description:
 */

import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.UserRelationDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.service.ApUserRelationService;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  14:41
 */
@Service
@Slf4j
public class ApUserRelationServiceImpl implements ApUserRelationService {
    @Autowired
    private CacheService cacheService;
    @Override
    public ResponseResult follow(UserRelationDto dto) {
        //1.检查参数
        if(dto==null||dto.getAuthorId()==null||dto.getOperation()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断是否登录
        ApUser user = AppThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        Integer apUserId = dto.getAuthorId();
        Integer followUserId  = dto.getAuthorId();
        if(dto.getOperation()==0){
            //将对方写入我的关注中
            cacheService.zAdd(BehaviorConstants.APUSER_FOLLOW_RELATION+apUserId,followUserId.toString(),System.currentTimeMillis());
            //将对方写入对方的粉丝中
            cacheService.zAdd(BehaviorConstants.APUSER_FANS_RELATION+followUserId,apUserId.toString(),System.currentTimeMillis());
        }else {
            cacheService.zRemove(BehaviorConstants.APUSER_FOLLOW_RELATION+apUserId,followUserId.toString());
            cacheService.zRemove(BehaviorConstants.APUSER_FANS_RELATION+followUserId,apUserId.toString());
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
