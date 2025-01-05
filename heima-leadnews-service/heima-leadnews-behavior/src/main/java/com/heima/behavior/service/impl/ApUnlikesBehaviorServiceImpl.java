package com.heima.behavior.service.impl;/**
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.heima.behavior.service.ApUnlikesBehaviorService;
import com.heima.common.constants.BehaviorConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
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
 *@CreateTime: 2025-01-05  13:16
 */
@Service
@Slf4j
@Transactional
public class ApUnlikesBehaviorServiceImpl implements ApUnlikesBehaviorService {
    @Autowired
    private CacheService cacheService;
    @Override
    public ResponseResult unlikesBehavior(UnLikesBehaviorDto dto) {
        //1.检查参数
        if(dto==null||dto.getArticleId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.获取用户信息
        ApUser user = AppThreadLocalUtil.getUser();
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //3.根据操作类型进行操作
        if(dto.getType()==0){
            cacheService.hPut(BehaviorConstants.UN_LIKE_BEHAVIOR+dto.getArticleId(),user.getId().toString(), JSON.toJSONString(dto));
        }else if(dto.getType()==1){
            cacheService.hDelete(BehaviorConstants.UN_LIKE_BEHAVIOR+dto.getArticleId(),user.getId().toString());
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
