package com.heima.behavior.service.impl;/**
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.heima.behavior.service.ApLikesBehaviorService;
import com.heima.common.constants.BehaviorConstants;
import com.heima.common.constants.HotArticleConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mess.UpdateArticleMess;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2025-01-04  15:54
 */
@Service
@Slf4j
@Transactional
public class ApLikesBehaviorServiceImpl implements ApLikesBehaviorService {
    @Autowired
    private CacheService cacheService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public ResponseResult likesBehavior(LikesBehaviorDto dto) {
        //1.检查参数
        if(dto==null||dto.getArticleId()==null||dto.getOperation()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.是否登录
        ApUser apUser  = AppThreadLocalUtil.getUser();
        if(apUser ==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        UpdateArticleMess mess = new UpdateArticleMess();
        mess.setArticleId(dto.getArticleId());
        mess.setType(UpdateArticleMess.UpdateArticleType.LIKES);

        //3.判断是否点赞
        if(dto.getOperation()==0){
            boolean b = cacheService.hGet(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(), apUser.getId().toString());
            if(b){
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"您已经点过赞了");
            }else{
                //4.点赞
                cacheService.hPut(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(),apUser.getId().toString(),"1");
                mess.setAdd(1);
            }
        }else if(dto.getOperation()==1){
            cacheService.hDelete(BehaviorConstants.LIKE_BEHAVIOR + dto.getArticleId().toString(),apUser.getId().toString());
            mess.setAdd(-1);
        }
        //4.发送消息
        kafkaTemplate.send(HotArticleConstants.HOT_ARTICLE_SCORE_TOPIC, JSONObject.toJSONString(mess));

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
